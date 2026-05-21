package com.spring.graphics.panels.map;



import java.awt.Point;
import java.util.List;

import com.spring.controllers.controllers.InitController;
import com.spring.graphics.panels.map.interfaces.IMap;
import com.spring.models.buildings.Building;
import com.spring.models.field.IRField;
import com.spring.models.field.IRoad;
import com.spring.models.layer.Ice;
import com.spring.models.layer.Layer;
import com.spring.models.layer.Snow;

public class MapGenerator {
    private final InitController initController;
    private final IMap map;

    public MapGenerator(InitController initController, IMap map) {
        this.initController = initController;
        this.map = map;
    }

    public void genMap1() {
        int step = 70;
        int paddingX = 60;
        int paddingY = 80;
        IRField field;
        IRoad crossRoad;
        Building building;
        
        field = initController.addField(new Snow(1), false); //0
        map.addField(field, new Point(paddingX, 4*step+paddingY));
        field = initController.addField(new Snow(2), false); //1
        map.addField(field, new Point(paddingX+step, 4*step+paddingY));
        field = initController.addField(new Ice(), false); //2
        map.addField(field, new Point(paddingX+2*step, 4*step+paddingY));
        field = initController.addField(new Layer(), false); //3
        map.addField(field, new Point(paddingX+3*step, 4*step+paddingY));
        field = initController.addField(new Snow(1), false); //4
        map.addField(field, new Point(paddingX+4*step, 4*step+paddingY));
        field = initController.addField(new Ice(), false); //5
        map.addField(field, new Point(paddingX, 5*step+paddingY));
        field = initController.addField(new Snow(2), false); //6
        map.addField(field, new Point(paddingX+step, 5*step+paddingY));
        field = initController.addField(new Snow(1), false); //7
        map.addField(field, new Point(paddingX+2*step, 5*step+paddingY));
        field = initController.addField(new Ice(), false); //8
        map.addField(field, new Point(paddingX+3*step, 5*step+paddingY));
        field = initController.addField(new Layer(), false); //9
        map.addField(field, new Point(paddingX+4*step, 5*step+paddingY));

        initController.setSide(true, 1, 0);
        initController.setSide(false, 1, 6);
        initController.setSide(true, 2, 7);
        initController.setSide(true, 3, 8);
        initController.setSide(true, 4, 9);
        
        initController.setFrontField(1, 2);
        initController.setFrontField(2, 3);
        initController.setFrontField(3, 4);
        initController.setFrontField(0, 5);
        initController.setFrontField(5, 6);
        initController.setFrontField(6, 7);
        initController.setFrontField(7, 8);
        initController.setFrontField(8, 9);

        field = initController.addField(new Snow(2), false); //10
        map.addField(field, new Point(paddingX+6*step, 4*step+paddingY));
        field = initController.addField(new Snow(1), false); //11
        map.addField(field, new Point(paddingX+6*step, 5*step+paddingY));
        crossRoad = initController.addCrossRoad(List.of(10, 11));
        map.addCrossRoad(crossRoad, new Point(paddingX+5*step, (int)(4.5*step+paddingY)));
        initController.setFrontCrossRoad(9, 0);
        initController.setFrontCrossRoad(4, 0);
        initController.setSide(true, 10, 11);

        field = initController.addField(new Ice(), false); //12
        map.addField(field, new Point(paddingX+7*step, 5*step+paddingY));
        field = initController.addField(new Layer(), false); //13
        map.addField(field, new Point(paddingX+8*step, 5*step+paddingY));
        field = initController.addField(new Snow(1), false); //14
        map.addField(field, new Point(paddingX+8*step, 4*step+paddingY));
        field = initController.addField(new Ice(), false); //15
        map.addField(field, new Point(paddingX+8*step, 3*step+paddingY));
        field = initController.addField(new Snow(2), false); //16
        map.addField(field, new Point(paddingX+8*step, 2*step+paddingY));
        field = initController.addField(new Snow(1), false); //17
        map.addField(field, new Point(paddingX+8*step, step+paddingY));
        field = initController.addField(new Snow(1), false); //18
        map.addField(field, new Point(paddingX+7*step, step+paddingY));
        field = initController.addField(new Snow(2), false); //19
        map.addField(field, new Point(paddingX+6*step, step+paddingY));
        field = initController.addField(new Ice(), false); //20
        map.addField(field, new Point(paddingX+5*step, step+paddingY));
        field = initController.addField(new Layer(), false); //21
        map.addField(field, new Point(paddingX+4*step, step+paddingY));
        field = initController.addField(new Snow(1), false); //22
        map.addField(field, new Point(paddingX+7*step, 4*step+paddingY));
        field = initController.addField(new Ice(), false); //23
        map.addField(field, new Point(paddingX+7*step, 3*step+paddingY));
        field = initController.addField(new Snow(2), false); //24
        map.addField(field, new Point(paddingX+7*step, 2*step+paddingY));
        field = initController.addField(new Snow(1), false); //25
        map.addField(field, new Point(paddingX+6*step, 2*step+paddingY));
        field = initController.addField(new Ice(), false); //26
        map.addField(field, new Point(paddingX+5*step, 2*step+paddingY));
        field = initController.addField(new Layer(), false); //27
        map.addField(field, new Point(paddingX+4*step, 2*step+paddingY));
        

        initController.setFrontField(11, 12);
        initController.setFrontField(12, 13);
        initController.setFrontField(13, 14);
        initController.setFrontField(14, 15);
        initController.setFrontField(15, 16);
        initController.setFrontField(16, 17);
        initController.setFrontField(17, 18);
        initController.setFrontField(18, 19);
        initController.setFrontField(19, 20);
        initController.setFrontField(20, 21);

        initController.setFrontField(10, 22);
        initController.setFrontField(22, 23);
        initController.setFrontField(23, 24);
        initController.setFrontField(24, 25);
        initController.setFrontField(25, 26);
        initController.setFrontField(26, 27);

        initController.setSide(true, 22, 12);
        initController.setSide(false, 22, 14);
        initController.setSide(true, 23, 15);
        initController.setSide(true, 24, 16);
        initController.setSide(false, 24, 18);
        initController.setSide(true, 25, 19);
        initController.setSide(true, 26, 20);
        initController.setSide(true, 27, 21);

        field = initController.addField(new Ice(), false); //28
        map.addField(field, new Point(paddingX+2*step, 2*step+paddingY));
        field = initController.addField(new Layer(), false); //29
        map.addField(field, new Point(paddingX+2*step, step+paddingY));
        initController.setSide(true, 28, 29);
        


        field = initController.addField(new Snow(1), false); //30
        map.addField(field, new Point(paddingX+step, step+paddingY));
        field = initController.addField(new Snow(1), false); //31
        map.addField(field, new Point(paddingX, step+paddingY));
        field = initController.addField(new Snow(2), false); //32
        map.addField(field, new Point(paddingX, 2*step+paddingY));
        field = initController.addField(new Ice(), false); //33
        map.addField(field, new Point(paddingX, 3*step+paddingY));
        field = initController.addField(new Layer(), false); //34
        map.addField(field, new Point(paddingX+step, 2*step+paddingY));
        field = initController.addField(new Snow(1), false); //35
        map.addField(field, new Point(paddingX+step, 3*step+paddingY));

        initController.setFrontField(29, 30);
        initController.setFrontField(30, 31);
        initController.setFrontField(31, 32);
        initController.setFrontField(32, 33);
        initController.setFrontField(33, 0);

        initController.setFrontField(28, 34);
        initController.setFrontField(34, 35);
        initController.setFrontField(35, 1);

        initController.setSide(true, 34, 30);
        initController.setSide(false, 34, 32);
        initController.setSide(true, 35, 33);

        field = initController.addField(new Layer(), false); //36
        map.addField(field, new Point(paddingX+3*step, 3*step+paddingY));
        field = initController.addField(new Snow(1), false); //37
        map.addField(field, new Point(paddingX+4*step, 3*step+paddingY));
        field = initController.addField(new Ice(), false); //38
        map.addField(field, new Point(paddingX+5*step, 3*step+paddingY));

        crossRoad = initController.addCrossRoad(List.of(28, 29, 36));//1
        map.addCrossRoad(crossRoad, new Point(paddingX+3*step, (int)(1.5*step+paddingY)));
        initController.setFrontCrossRoad(21, 1);
        initController.setFrontCrossRoad(27, 1);
      
        initController.setFrontField(36, 37);
        initController.setFrontField(37, 38);
        initController.setFrontCrossRoad(38, 0);

        //Buildings
        building = initController.addHome(5);
        map.addBuilding(building);
        building = initController.addHome(12);
        map.addBuilding(building);
        building = initController.addHome(16);
        map.addBuilding(building);
        building = initController.addHome(20);
        map.addBuilding(building);
        building = initController.addHome(30);
        map.addBuilding(building);
        building = initController.addHome(33);
        map.addBuilding(building);

        building = initController.addOffice(2);
        map.addBuilding(building);
        building = initController.addOffice(14);
        map.addBuilding(building);
        building = initController.addOffice(19);
        map.addBuilding(building);
        building = initController.addOffice(31);
        map.addBuilding(building);
        building = initController.addOffice(35);
        map.addBuilding(building);
        building = initController.addOffice(0);
        map.addBuilding(building);

        List<Building> stations = initController.addStations(2, 24);
        map.addBuilding(stations.get(0));
        map.addBuilding(stations.get(1));
        stations = initController.addStations(28, 10);
        map.addBuilding(stations.get(0));
        map.addBuilding(stations.get(1));
    }

    public void genMap2()
    {
        int step = 70;
        int paddingX = 20;
        int paddingY = 20;
        IRField field;
        IRoad crossRoad;
        Building building;

        //between crossroad 3 and 0
        field = initController.addField(new Snow(1), false); //0
        map.addField(field, new Point(paddingX+3*step, paddingY));
        field = initController.addField(new Snow(2), false); //1
        map.addField(field, new Point(paddingX+4*step, paddingY));
        field = initController.addField(new Ice(), false); //2
        map.addField(field, new Point(paddingX+5*step, paddingY));
        field = initController.addField(new Layer(), false); //3
        map.addField(field, new Point(paddingX+6*step, paddingY));
        field = initController.addField(new Snow(1), false); //4
        map.addField(field, new Point(paddingX+3*step, paddingY+step));

        field = initController.addField(new Ice(), false); //5
        map.addField(field, new Point(paddingX+4*step, paddingY+step));
        field = initController.addField(new Snow(2), false); //6
        map.addField(field, new Point(paddingX+5*step, paddingY+step));
        field = initController.addField(new Snow(1), false); //7
        map.addField(field, new Point(paddingX+6*step, paddingY+step));
        field = initController.addField(new Ice(), false); //8
        map.addField(field, new Point(paddingX+6*step, paddingY+2*step));
        field = initController.addField(new Layer(), false); //9
        map.addField(field, new Point(paddingX+5*step, paddingY+2*step));
        field = initController.addField(new Snow(2), false); //10
        map.addField(field, new Point(paddingX+4*step, paddingY+2*step));
        field = initController.addField(new Snow(1), false); //11
        map.addField(field, new Point(paddingX+3*step, paddingY+2*step));

        initController.setFrontField(0, 1);
        initController.setFrontField(1, 2);
        initController.setFrontField(2, 3);
        initController.setFrontField(4, 5);
        initController.setFrontField(5, 6);
        initController.setFrontField(6, 7);
        initController.setFrontField(8, 9);
        initController.setFrontField(9, 10);
        initController.setFrontField(10, 11);

        initController.setSide(true, 0, 4);
        initController.setSide(true, 1, 5);
        initController.setSide(true, 2, 6);
        initController.setSide(true, 3, 7);

        //crossroad 0
        field = initController.addField(new Layer(), false); //12
        map.addField(field, new Point(paddingX+8*step, paddingY+3*step));
        field = initController.addField(new Snow(2), false); //13
        map.addField(field, new Point(paddingX+9*step, paddingY+3*step));
        crossRoad = initController.addCrossRoad(List.of(12, 13, 8));
        map.addCrossRoad(crossRoad, new Point(paddingX+8*step, paddingY+step));
        initController.setFrontCrossRoad(3, 0);
        initController.setFrontCrossRoad(7, 0);
        initController.setSide(true, 13, 12);

        //between crossroad 0 and 1
        field = initController.addField(new Ice(), false); //14
        map.addField(field, new Point(paddingX+9*step, paddingY+4*step));
        field = initController.addField(new Layer(), false); //15
        map.addField(field, new Point(paddingX+9*step, paddingY+5*step));
        field = initController.addField(new Ice(), false); //16
        map.addField(field, new Point(paddingX+8*step, paddingY+4*step));
        field = initController.addField(new Layer(), false); //17
        map.addField(field, new Point(paddingX+8*step, paddingY+5*step));
        field = initController.addField(new Layer(), false); //18
        map.addField(field, new Point(paddingX+7*step, paddingY+5*step));
        field = initController.addField(new Ice(), false); //19
        map.addField(field, new Point(paddingX+7*step, paddingY+4*step));
        field = initController.addField(new Layer(), false); //20
        map.addField(field, new Point(paddingX+7*step, paddingY+3*step));

        initController.setFrontField(13, 14);
        initController.setFrontField(14, 15);
        initController.setFrontField(12, 16);
        initController.setFrontField(16, 17);
        initController.setFrontField(18, 19);
        initController.setFrontField(19, 20);

        initController.setSide(true, 14, 16);
        initController.setSide(true, 15, 17);
        
        initController.setFrontCrossRoad(20, 0);

        //crossroad 1
        field = initController.addField(new Snow(1), false); //21
        map.addField(field, new Point(paddingX+6*step, paddingY+7*step));
        field = initController.addField(new Snow(2), false); //22
        map.addField(field, new Point(paddingX+6*step, paddingY+8*step));
        crossRoad = initController.addCrossRoad(List.of(21, 22, 18));
        map.addCrossRoad(crossRoad, new Point(paddingX+8*step, paddingY+7*step));
        initController.setFrontCrossRoad(15, 1);
        initController.setFrontCrossRoad(17, 1);
        initController.setSide(true, 22, 21);

        //between crossroad 1 and 2
        field = initController.addField(new Ice(), false); //23
        map.addField(field, new Point(paddingX+5*step, paddingY+8*step));
        field = initController.addField(new Layer(), false); //24
        map.addField(field, new Point(paddingX+4*step, paddingY+8*step));
        field = initController.addField(new Snow(1), false); //25
        map.addField(field, new Point(paddingX+3*step, paddingY+8*step));
        field = initController.addField(new Ice(), false); //26
        map.addField(field, new Point(paddingX+5*step, paddingY+7*step));
        field = initController.addField(new Snow(2), false); //27
        map.addField(field, new Point(paddingX+4*step, paddingY+7*step));
        field = initController.addField(new Snow(1), false); //28
        map.addField(field, new Point(paddingX+3*step, paddingY+7*step));
        field = initController.addField(new Ice(), false); //29
        map.addField(field, new Point(paddingX+3*step, paddingY+6*step));
        field = initController.addField(new Layer(), false); //30
        map.addField(field, new Point(paddingX+4*step, paddingY+6*step));
        field = initController.addField(new Snow(2), false); //31
        map.addField(field, new Point(paddingX+5*step, paddingY+6*step));
        field = initController.addField(new Snow(1), false); //32
        map.addField(field, new Point(paddingX+6*step, paddingY+6*step));

        initController.setSide(true, 23, 26);
        initController.setSide(true, 24, 27);
        initController.setSide(true, 25, 28);
        
        initController.setFrontField(22, 23);
        initController.setFrontField(23, 24);
        initController.setFrontField(24, 25);

        initController.setFrontField(21, 26);
        initController.setFrontField(26, 27);
        initController.setFrontField(27, 28);

        initController.setFrontField(29, 30);
        initController.setFrontField(30, 31);
        initController.setFrontField(31, 32);

        initController.setFrontCrossRoad(32, 1);

        //crossroad 2
        field = initController.addField(new Ice(), false); //33
        map.addField(field, new Point(paddingX+step, paddingY+5*step));
        field = initController.addField(new Layer(), false); //34
        map.addField(field, new Point(paddingX, paddingY+5*step));
        crossRoad = initController.addCrossRoad(List.of(33, 34, 29));
        map.addCrossRoad(crossRoad, new Point(paddingX+step, paddingY+7*step));
        initController.setFrontCrossRoad(28, 2);
        initController.setFrontCrossRoad(25, 2);
        initController.setSide(true, 34, 33);

        //between crossroad 2 and 3
        field = initController.addField(new Snow(1), false); //35
        map.addField(field, new Point(paddingX, paddingY+4*step));
        field = initController.addField(new Ice(), false); //36
        map.addField(field, new Point(paddingX, paddingY+3*step));
        field = initController.addField(new Snow(2), false); //37
        map.addField(field, new Point(paddingX+step, paddingY+4*step));
        field = initController.addField(new Snow(1), false); //38
        map.addField(field, new Point(paddingX+step, paddingY+3*step));
        field = initController.addField(new Ice(), false); //39
        map.addField(field, new Point(paddingX+2*step, paddingY+3*step));
        field = initController.addField(new Layer(), false); //40
        map.addField(field, new Point(paddingX+2*step, paddingY+4*step));
        field = initController.addField(new Snow(2), false); //41
        map.addField(field, new Point(paddingX+2*step, paddingY+5*step));

        initController.setSide(true, 35, 37);
        initController.setSide(true, 36, 38);
       
        initController.setFrontField(34, 35);
        initController.setFrontField(35, 36);

        initController.setFrontField(33, 37);
        initController.setFrontField(37, 38);

        initController.setFrontField(39, 40);
        initController.setFrontField(40, 41);

        initController.setFrontCrossRoad(41, 2);

        //crossroad 3
        crossRoad = initController.addCrossRoad(List.of(0, 4, 39));
        map.addCrossRoad(crossRoad, new Point(paddingX+step, paddingY+step));
        initController.setFrontCrossRoad(36, 3);
        initController.setFrontCrossRoad(38, 3);
        initController.setFrontCrossRoad(11, 3);

        //buildings
        
        building = initController.addHome(0);
        map.addBuilding(building);
        building = initController.addHome(8);
        map.addBuilding(building);
        building = initController.addHome(14);
        map.addBuilding(building);
        building = initController.addHome(22);
        map.addBuilding(building);
        building = initController.addHome(29);
        map.addBuilding(building);
        building = initController.addHome(35);
        map.addBuilding(building);

        
        building = initController.addOffice(2);
        map.addBuilding(building);
        building = initController.addOffice(13);
        map.addBuilding(building);
        building = initController.addOffice(18);
        map.addBuilding(building);
        building = initController.addOffice(25);
        map.addBuilding(building);
        building = initController.addOffice(40);
        map.addBuilding(building);
        building = initController.addOffice(34);
        map.addBuilding(building);

        List<Building> stations = initController.addStations(10, 31);
        map.addBuilding(stations.get(0));
        map.addBuilding(stations.get(1));
        stations = initController.addStations(15, 36);
        map.addBuilding(stations.get(0));
        map.addBuilding(stations.get(1));
        stations = initController.addStations(20, 24);
        map.addBuilding(stations.get(0));
        map.addBuilding(stations.get(1));
        stations = initController.addStations(41, 8);
        map.addBuilding(stations.get(0));
        map.addBuilding(stations.get(1));

        
    }

    public void genMap3(){
        initController.addField(new Snow(1), false); //0
        initController.addField(new Snow(2), false); //1
        initController.addField(new Ice(), false); //2
        initController.addField(new Layer(), false); //3

        initController.setFrontField(0 , 1);
        initController.setFrontField(2, 3);

        initController.addField(new Snow(1), false); //4
        initController.addField(new Ice(), false); //5
        initController.addField(new Snow(1), false); //6

        initController.addCrossRoad(List.of(2, 4, 5, 6));//0
        initController.setFrontCrossRoad(1, 0);

        initController.addField(new Snow(1), false); //7
        initController.addField(new Ice(), false); //8
        initController.addField(new Layer(), false); //9

        initController.setFrontField(4, 7);
        initController.setFrontField(8, 9);
        initController.setFrontCrossRoad(9, 0);

        initController.addField(new Snow(2), false); //10
        initController.addField(new Snow(1), false); //11
        initController.addField(new Layer(), false); //12

        initController.setFrontField(5, 10);
        initController.setFrontField(11, 12);
        initController.setFrontCrossRoad(12, 0);

        initController.addField(new Snow(2), false); //13
        initController.addField(new Ice(), false); //14
        initController.addField(new Layer(), false); //15

        initController.addCrossRoad(List.of(11, 13, 14, 15)); //1
        initController.setFrontCrossRoad(10, 1);

        initController.addField(new Ice(), false); //16
        initController.addField(new Layer(), false); //17
        initController.addField(new Layer(), false); //18

        initController.setFrontField(13, 16);
        initController.setFrontField(17, 18);
        initController.setFrontCrossRoad(18, 1);

        initController.addField(new Ice(), false); //19
        initController.addField(new Layer(), false); //20
        initController.addField(new Snow(1), false); //21

        initController.setFrontField(14, 19);
        initController.setFrontField(20, 21);
        initController.setFrontCrossRoad(21, 1);

        initController.addField(new Snow(2), false); //22
        initController.addField(new Ice(), false); //23
        initController.addField(new Layer(), false); //24

        initController.setFrontField(15, 22);
        initController.setFrontField(23, 24);
        initController.setFrontCrossRoad(24, 1);

        initController.addField(new Snow(1), false); //25
        initController.addField(new Ice(), false); //26
        initController.addField(new Snow(2), false); //27

        initController.setFrontField(6, 25);
        initController.setFrontField(26, 27);
        initController.setFrontCrossRoad(27, 0);

        //kulso kor
        initController.addField(new Snow(1), false); //28
        initController.addCrossRoad(List.of(26, 28));//2

        initController.addField(new Ice(), false); //29
        initController.addField(new Layer(), false); //30
        initController.addField(new Snow(2), false); //31

        initController.setFrontField(28, 29);
        initController.setFrontField(30, 31);
        initController.setFrontCrossRoad(31, 2);

        initController.addField(new Snow(1), false); //32
        initController.addCrossRoad(List.of(30, 23, 32)); //3
        initController.setFrontCrossRoad(29, 3);
        initController.setFrontCrossRoad(22, 3);

        initController.addField(new Ice(), false); //33
        initController.addField(new Layer(), false); //34
        initController.setFrontField(32, 33);
        initController.setFrontField(33, 34);

        initController.addField(new Snow(1), false); //35
        initController.addCrossRoad(List.of(20, 35)); //4
        initController.setFrontCrossRoad(19, 4);
        initController.setFrontCrossRoad(34, 4);

        initController.addField(new Ice(), false); //36
        initController.addField(new Snow(2), false); //37
        initController.setFrontField(35, 36);
        initController.setFrontField(36, 37);

        initController.addField(new Snow(1), false); //38
        initController.addCrossRoad(List.of(17, 38));//5
        initController.setFrontCrossRoad(37, 5);
        initController.setFrontCrossRoad(16, 5);

        initController.addField(new Ice(), false); //39
        initController.addField(new Layer(), false); //40
        initController.addField(new Snow(2), false); //41
        initController.setFrontField(38, 39);
        initController.setFrontField(40, 41);
        initController.setFrontCrossRoad(41, 5);

        initController.addField(new Snow(1), false); //42
        initController.addCrossRoad(List.of(40, 8, 42)); //6
        initController.setFrontCrossRoad(7, 6);
        initController.setFrontCrossRoad(39, 6);

        initController.addField(new Snow(2), false); //43
        initController.addField(new Snow(1), false); //44
        initController.setFrontField(42, 43);
        initController.setFrontField(43, 44);

        initController.addField(new Snow(2), false); //45
        initController.addCrossRoad(List.of(0, 45)); //7
        initController.setFrontCrossRoad(44, 7);
        initController.setFrontCrossRoad(3, 7);

        initController.addField(new Ice(), false); //46
        initController.addField(new Layer(), false); //47
        initController.setFrontField(45, 46);
        initController.setFrontField(46, 47);
        initController.setFrontCrossRoad(47, 2);

        //buildings
        initController.addHome(0);
        initController.addHome(8);
        initController.addHome(11);
        initController.addHome(25);
        initController.addHome(17);
        initController.addHome(19);
        initController.addHome(22);
        initController.addHome(38);
        initController.addHome(32);

        initController.addOffice(2);
        initController.addOffice(9);
        initController.addOffice(12);
        initController.addOffice(27);
        initController.addOffice(18);
        initController.addOffice(21);
        initController.addOffice(24);
        initController.addOffice(47);
        initController.addOffice(35);

        initController.addStations(10, 45);
        initController.addStations(15, 43);
        initController.addStations(20, 0);
        initController.addStations(41, 23);
        initController.addStations(33, 3);
        initController.addStations(30, 7);
    }
}
