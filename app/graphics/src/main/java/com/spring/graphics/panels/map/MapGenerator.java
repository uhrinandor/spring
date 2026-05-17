package com.spring.graphics.panels.map;



import java.util.List;

import com.spring.controllers.controllers.InitController;
import com.spring.models.layer.Ice;
import com.spring.models.layer.Layer;
import com.spring.models.layer.Snow;

public class MapGenerator {
    private final InitController initController;

    public MapGenerator(InitController initController) {
        this.initController = initController;
    }

    public void genMap1() {
        initController.addField(new Snow(1), false); //0
        initController.addField(new Snow(2), false); //1
        initController.addField(new Ice(), false); //2
        initController.addField(new Layer(), false); //3
        initController.addField(new Snow(1), false); //4
        initController.addField(new Ice(), false); //5
        initController.addField(new Snow(2), false); //6
        initController.addField(new Snow(1), false); //7
        initController.addField(new Ice(), false); //8
        initController.addField(new Layer(), false); //9

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

        initController.addField(new Snow(2), false); //10
        initController.addField(new Snow(1), false); //11
        initController.addCrossRoad(List.of(10, 11));
        initController.setFrontCrossRoad(9, 0);
        initController.setFrontCrossRoad(4, 0);
        initController.setSide(true, 10, 11);

        initController.addField(new Ice(), false); //12
        initController.addField(new Layer(), false); //13
        initController.addField(new Snow(1), false); //14
        initController.addField(new Ice(), false); //15
        initController.addField(new Snow(2), false); //16
        initController.addField(new Snow(1), false); //17
        initController.addField(new Snow(1), false); //18
        initController.addField(new Snow(2), false); //19
        initController.addField(new Ice(), false); //20
        initController.addField(new Layer(), false); //21
        initController.addField(new Snow(1), false); //22
        initController.addField(new Ice(), false); //23
        initController.addField(new Snow(2), false); //24
        initController.addField(new Snow(1), false); //25
        initController.addField(new Ice(), false); //26
        initController.addField(new Layer(), false); //27
        

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

        initController.addField(new Ice(), false); //28
        initController.addField(new Layer(), false); //29
        initController.setSide(true, 28, 29);


        initController.addField(new Snow(1), false); //30
        initController.addField(new Snow(1), false); //31
        initController.addField(new Snow(2), false); //32
        initController.addField(new Ice(), false); //33
        initController.addField(new Layer(), false); //34
        initController.addField(new Snow(1), false); //35

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

        initController.addField(new Layer(), false); //36
        initController.addField(new Snow(1), false); //37
        initController.addField(new Ice(), false); //38

        initController.addCrossRoad(List.of(28, 29, 36));//1
        initController.setFrontCrossRoad(21, 1);
        initController.setFrontCrossRoad(27, 1);
      
        initController.setFrontField(36, 37);
        initController.setFrontField(37, 38);
        initController.setFrontCrossRoad(38, 0);

        //Buildings
        initController.addHome(5);
        initController.addHome(12);
        initController.addHome(16);
        initController.addHome(20);
        initController.addHome(30);
        initController.addHome(33);

        initController.addOffice(2);
        initController.addOffice(14);
        initController.addOffice(19);
        initController.addOffice(31);
        initController.addOffice(35);
        initController.addOffice(0);

        initController.addStations(2, 24);
        initController.addStations(28, 10);

    }

    public void genMap2()
    {
        //between crossroad 3 and 0
        initController.addField(new Snow(1), false); //0
        initController.addField(new Snow(2), false); //1
        initController.addField(new Ice(), false); //2
        initController.addField(new Layer(), false); //3
        initController.addField(new Snow(1), false); //4
        initController.addField(new Ice(), false); //5
        initController.addField(new Snow(2), false); //6
        initController.addField(new Snow(1), false); //7
        initController.addField(new Ice(), false); //8
        initController.addField(new Layer(), false); //9
        initController.addField(new Snow(2), false); //10
        initController.addField(new Snow(1), false); //11
        

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

        initController.setSide(true, 4, 11);
        initController.setSide(true, 5, 10);
        initController.setSide(true, 6, 9);
        initController.setSide(true, 7, 8);

        //crossroad 0
        initController.addField(new Layer(), false); //12
        initController.addField(new Snow(2), false); //13
        initController.addCrossRoad(List.of(12, 13, 8));
        initController.setFrontCrossRoad(3, 0);
        initController.setFrontCrossRoad(7, 0);
        initController.setSide(true, 13, 12);

        //between crossroad 0 and 1
        initController.addField(new Ice(), false); //14
        initController.addField(new Layer(), false); //15
        initController.addField(new Ice(), false); //16
        initController.addField(new Layer(), false); //17
        initController.addField(new Layer(), false); //18
        initController.addField(new Ice(), false); //19
        initController.addField(new Layer(), false); //20

        initController.setFrontField(13, 14);
        initController.setFrontField(14, 15);
        initController.setFrontField(12, 16);
        initController.setFrontField(16, 17);
        initController.setFrontField(18, 19);
        initController.setFrontField(19, 20);
        
        initController.setFrontCrossRoad(20, 0);

        //crossroad 1
        initController.addField(new Snow(1), false); //21
        initController.addField(new Snow(2), false); //22
        initController.addCrossRoad(List.of(21, 22, 18));
        initController.setFrontCrossRoad(15, 1);
        initController.setFrontCrossRoad(17, 1);
        initController.setSide(true, 22, 21);

        //between crossroad 1 and 2
        initController.addField(new Ice(), false); //23
        initController.addField(new Layer(), false); //24
        initController.addField(new Snow(1), false); //25
        initController.addField(new Ice(), false); //26
        initController.addField(new Snow(2), false); //27
        initController.addField(new Snow(1), false); //28
        initController.addField(new Ice(), false); //29
        initController.addField(new Layer(), false); //30
        initController.addField(new Snow(2), false); //31
        initController.addField(new Snow(1), false); //32

        initController.setSide(true, 23, 26);
        initController.setSide(true, 24, 27);
        initController.setSide(true, 25, 28);

        initController.setSide(true, 21, 32);
        initController.setSide(true, 26, 31);
        initController.setSide(true, 27, 30);
        initController.setSide(true, 28, 29);
        
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
        initController.addField(new Ice(), false); //33
        initController.addField(new Layer(), false); //34
        initController.addCrossRoad(List.of(33, 34, 29));
        initController.setFrontCrossRoad(28, 2);
        initController.setFrontCrossRoad(25, 2);
        initController.setSide(true, 34, 33);

        //between crossroad 2 and 3
        initController.addField(new Snow(1), false); //35
        initController.addField(new Ice(), false); //36
        initController.addField(new Snow(2), false); //37
        initController.addField(new Snow(1), false); //38
        initController.addField(new Ice(), false); //39
        initController.addField(new Layer(), false); //40
        initController.addField(new Snow(2), false); //41

        initController.setSide(true, 35, 37);
        initController.setSide(true, 36, 38);

        initController.setSide(true, 33, 41);
        initController.setSide(true, 37, 40);
        initController.setSide(true, 38, 39);
       
        initController.setFrontField(34, 35);
        initController.setFrontField(35, 36);

        initController.setFrontField(33, 37);
        initController.setFrontField(37, 38);

        initController.setFrontField(39, 40);
        initController.setFrontField(40, 41);

        initController.setFrontCrossRoad(41, 2);

        //crossroad 3
        initController.addCrossRoad(List.of(0, 4, 39));
        initController.setFrontCrossRoad(36, 3);
        initController.setFrontCrossRoad(38, 3);
        initController.setFrontCrossRoad(11, 3);

        //buildings
        initController.addHome(0);
        initController.addHome(8);
        initController.addHome(14);
        initController.addHome(22);
        initController.addHome(29);
        initController.addHome(35);

        initController.addOffice(2);
        initController.addOffice(13);
        initController.addOffice(18);
        initController.addOffice(25);
        initController.addOffice(40);
        initController.addOffice(34);

        initController.addStations(10, 31);
        initController.addStations(15, 36);
        initController.addStations(20, 24);
        initController.addStations(41, 8);

        
    }

    public void genMap3()
    {}
}
