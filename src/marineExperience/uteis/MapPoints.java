package marineExperience.uteis;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import marineExperience.model.Ais;
import marineExperience.model.Barco;
import marineExperience.service.AisService;
import marineExperience.view.MapPrincipalView;
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

public class MapPoints {

    private final Set<SwingWaypoint> waypoints = new HashSet<SwingWaypoint>();
    private final JXMapViewer mapViewer = new JXMapViewer();
    private final WaypointPainter<SwingWaypoint> swingWaypointPainter = new SwingWaypointOverlayPainter();
    private MouseInputListener mia;
    private MapPrincipalView mpv;
    
    public JXMapViewer RetornaPoints(List<Ais> aiss, MapPrincipalView mpv) {
        this.mpv = mpv;
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

        for (int i = 0; i < aiss.size(); i++) {
            //chama requisicao a quantidade de vezes precisar
            AisService aisService = new AisService(this);
            aisService.Post_JSON(aiss.get(i).getMsg());
        }
        
//        aiss.forEach(x -> {
//        });

        // Add interacao de arrastar e zoom
        addInteracao();
       
        return mapViewer;
    }
    
    public void addWaypoint(Barco boat){
        //na requisicao post, trago de volta barco a barco, add nas listas e seguindo o fluxo
        GeoPosition newPosition = new GeoPosition(boat.getLatitude(), boat.getLongitude());
        SwingWaypoint swp = new SwingWaypoint(boat.getMmsi(), boat.getTrueHeading(), boat.getRadioChannelCode(), newPosition);
        waypoints.add(swp);
        swingWaypointPainter.setWaypoints(waypoints);
        mapViewer.setOverlayPainter(swingWaypointPainter);
        // Add label no map viewer
        
        waypoints.forEach(w -> {
            //System.out.println(w.getLabel());
            mapViewer.add(w.getLabel());
            //this.mpv.iniciaMap(mapViewer);
        });
        //time
        this.mpv.iniciaMap(mapViewer);
    }
    
    public void addInteracao(){
        //interação de zoom e arrastar
        mia = new PanMouseInputListener(mapViewer);
        mapViewer.addMouseListener(mia);
        mapViewer.addMouseMotionListener(mia);
        mapViewer.addMouseWheelListener(new ZoomMouseWheelListenerCursor(mapViewer));
    }
}
