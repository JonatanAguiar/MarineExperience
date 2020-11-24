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

    public JXMapViewer RetornaPoints(List<Ais> aiss) {
        File cacheDir = new File(System.getProperty("user.home") + File.separator + ".jxmapviewer2");
        //https://github.com/msteiger/jxmapviewer2/tree/master/examples/src
        JXMapViewer mapViewer = new JXMapViewer();
        return RetornaNewPoints(mapViewer, aiss);
    }

    public JXMapViewer RetornaNewPoints(JXMapViewer mapViewer, List<Ais> aiss) {
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
            AisService aisService = new AisService();
            Barco boat = aisService.Post_JSON(x.getMsg());
            GeoPosition newPosition = new GeoPosition(boat.getLatitude(), boat.getLongitude());
            SwingWaypoint swp = new SwingWaypoint(boat.getMmsi(), boat.getTrueHeading(), boat.getRadioChannelCode(), newPosition);
            waypoints.add(swp);
        });

        // Add interactions
        MouseInputListener mia = new PanMouseInputListener(mapViewer);
        mapViewer.addMouseListener(mia);
        mapViewer.addMouseMotionListener(mia);
        mapViewer.addMouseWheelListener(new ZoomMouseWheelListenerCursor(mapViewer));

        WaypointPainter<SwingWaypoint> swingWaypointPainter = new SwingWaypointOverlayPainter();
        swingWaypointPainter.setWaypoints(waypoints);
        mapViewer.setOverlayPainter(swingWaypointPainter);

        // Add the boats to the map viewer
        for (SwingWaypoint w : waypoints) {
            mapViewer.add(w.getLabel());
        }

        return mapViewer;
    }
}
