/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication31.uteis;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javaapplication31.model.Ais;
import javaapplication31.model.Barco;
import javaapplication31.service.AisService;
import javaapplication31.view.MapPrincipalView;
import javax.swing.event.MouseInputListener;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.cache.FileBasedLocalCache;
import org.jxmapviewer.input.PanMouseInputListener;
import org.jxmapviewer.input.ZoomMouseWheelListenerCursor;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.TileFactoryInfo;
import org.jxmapviewer.viewer.WaypointPainter;

/**
 *
 * @author Dell
 */
public class MapPoints {

    private Set<SwingWaypoint> waypoints = new HashSet<SwingWaypoint>();
    private JXMapViewer mapViewer = new JXMapViewer();
    private List<Ais> aiss;
    private WaypointPainter<SwingWaypoint> swingWaypointPainter = new SwingWaypointOverlayPainter();;
    private MouseInputListener mia;
    private MapPrincipalView mpv;
    
    public JXMapViewer RetornaPoints(List<Ais> aiss, MapPrincipalView mpv) {
        this.mpv = mpv;
        this.aiss = aiss;
        File cacheDir = new File(System.getProperty("user.home") + File.separator + ".jxmapviewer2");

        // Create a TileFactoryInfo for OpenStreetMap
        TileFactoryInfo info = new OSMTileFactoryInfo();
        DefaultTileFactory tileFactory = new DefaultTileFactory(info);
        mapViewer.setTileFactory(tileFactory);
        tileFactory.setLocalCache(new FileBasedLocalCache(cacheDir, false));

        // Use 8 threads in parallel to load the tiles
        tileFactory.setThreadPoolSize(8);

        // Set the focus
        mapViewer.setZoom(9);
        GeoPosition litoral = new GeoPosition(-29.5, -49.4);
        mapViewer.setAddressLocation(litoral);

        aiss.forEach(x -> {
            //chama requisicao a quantidade de vezes precisar
            AisService aisService = new AisService();
            aisService.Post_JSON(x.getMsg(), this);
        });

        // Add interacao de arrastar e zoom
        //primeira vez add
        if(true==true){
            addInteracao();
        }

        return mapViewer;
    }
    
    public void addWaypoint(Barco boat){
        //na requisicao post, trago de volta barco a barco, add nas listas e seguindo o fluxo
        GeoPosition newPosition = new GeoPosition(boat.getLatitude(), boat.getLongitude());
        SwingWaypoint swp = new SwingWaypoint(boat.getMmsi(), boat.getTrueHeading(), boat.getRadioChannelCode(), newPosition);
        waypoints.add(swp);
        swingWaypointPainter.setWaypoints(waypoints);
        mapViewer.setOverlayPainter(swingWaypointPainter);
        // Add the boats to the map viewer
        for (SwingWaypoint w : waypoints) {
            mapViewer.add(w.getLabel());
        }
        this.mpv.iniciaMap(mapViewer);
    }
    
    public void addInteracao(){
        mia = new PanMouseInputListener(mapViewer);
        mapViewer.addMouseListener(mia);
        mapViewer.addMouseMotionListener(mia);
        mapViewer.addMouseWheelListener(new ZoomMouseWheelListenerCursor(mapViewer));
    }
}
