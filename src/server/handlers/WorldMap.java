package server.handlers;
import java.io.*;
import server.root.server;

// Imports by Phate/WinterLove import java.util.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.*;

import server.resources.misc;

public class WorldMap {

   public static final int maxObjects = 2000000;
   public static int[] objectId = new int [maxObjects];
   public static int[] objectX = new int [maxObjects];
   public static int[] objectY = new int [maxObjects];
   public static int[] objectHeight = new int [maxObjects];
   public static int[] objectType = new int [maxObjects];
   public static int[] objectFace = new int [maxObjects];

// Vars by Phate/WinterLove, renamed & edit by WhiteFang  
   private static String worldMapDir = "config/worldmap";
   private static String worldMapFiles[] = {
      "World1.xml", "World2.xml", "World3.xml",
      "World4.xml", "World5.xml", "World6.xml",
      "World7.xml", "World8.xml", "World9.xml",
      "World10.xml", "World11.xml", "World12.xml",
      "World13.xml", "World14.xml", "World15.xml",
      "World16.xml", "World17.xml", "World18.xml",
      "World19.xml", "World20.xml", "World21.xml",
      "World22.xml", "World23.xml", "World24.xml",
      "World25.xml"
   };
   private static String worldMapNames[] = new String[worldMapFiles.length];
   private static Document worldMaps[] = new Document[worldMapFiles.length];
   private static int cWorldMap = 0;
   private static int cWorldMap2 = 0;
   public static void reset() {
      for (int i = 0; i < maxObjects; i++) {
         objectId[i] = -1;
         objectX[i] = -1;
         objectY[i] = -1;
         objectHeight[i] = -1;
         objectType[i] = -1;
         objectFace[i] = -1;
      }
   }

// loadDatabases() by Phate/WinterLove, renamed & edit by WhiteFang 
   public static void loadWorldMap() {
RandomAccessFile in = null;
		int counter = 0;
		int toadd = 70;
		byte[] cache = null;
		int ptr = 0;
		long a = System.currentTimeMillis();
         try {
			in = new RandomAccessFile("./config/worldmap.bin", "r");
			cache = new byte[(int) in.length()];
			in.read(cache, 0, (int) in.length());
			in.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		long took = System.currentTimeMillis()-a;
		server.SystemStream.println("Loaded WorldMap In ("+took+" ms)... ");
		for(int i = 0; i < 1280618; i++/*,j++*/) {
			try {
				objectId[i] = (((cache[ptr++] & 0xFF) << 8) | (cache[ptr++] & 0xFF));
				objectX[i] = (((cache[ptr++] & 0xFF) << 8) | (cache[ptr++] & 0xFF));
				objectY[i] = (((cache[ptr++] & 0xFF) << 8) | (cache[ptr++] & 0xFF));
				objectHeight[i] = cache[ptr++] & 0xFF;
				objectType[i] = cache[ptr++] & 0xFF;
				objectFace[i] = cache[ptr++] & 0xFF;
			} catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		generateWalkableGrid();
   }

// prepareDatabaseFile() by Phate/WinterLove, renamed & edit by WhiteFang 
   private static boolean prepareWorldMap(String fileName){
      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
      File dbFile = new File("./" + worldMapDir + "/" + fileName);
      if(!dbFile.exists() || fileName.indexOf(".xml") < 0) {
         misc.println("[ERROR]: Unable to load world map: " + fileName);
         return false;
      }
      try {
         DocumentBuilder db = dbf.newDocumentBuilder();
         worldMaps[cWorldMap] = db.parse("./" + worldMapDir + "/" + fileName);
         worldMapNames[cWorldMap] = fileName.substring(0, fileName.indexOf("."));
      } catch(Exception _ex) {
         return false;
      }
      cWorldMap++;
      return true;
   }

// releaseDatabase() by Phate/WinterLove, renamed & edit by WhiteFang 
   private static boolean releaseWorldMap(String worldMapName) {
      for(int i = 0; i < worldMapNames.length; i++) {
         if(worldMapNames[i].equalsIgnoreCase(worldMapName)) {
            worldMaps[i] = null;
            System.gc();
            return true;
         }
      }
      return false;
   }

// loadWorldDataBase() by Phate/WinterLove, renamed & edit by WhiteFang 
   private static void loadWorldMap(Document worldMap) {
      if(worldMap == null) {
         misc.println("[ERROR]: world map not found.");
         return ;
      }
      NodeList worldNl = worldMap.getElementsByTagName("worlddb");
      if(worldNl.item(0) == null) {
         return ;
      }
      Element worldMaster = (Element) worldNl.item(0);
      Element worldElement = worldMap.getDocumentElement();
      NodeList nl = worldElement.getElementsByTagName("tile");
      if(nl != null && nl.getLength() > 0) {
         for(int i = 0 ; i < nl.getLength(); i++) {
            try {
               Element tile = (Element) nl.item(i);
               if(tile == null) continue;
               int height = Integer.parseInt(tile.getAttribute("height"));
               if(height == -1) continue;
               int tileX = Integer.parseInt(tile.getAttribute("x"));
               int tileY = Integer.parseInt(tile.getAttribute("y"));
               int type = getNumberValue(tile, "type");
               int object = getNumberValue(tile, "object");
               int orientation = getNumberValue(tile, "orientation");
               addObject(tileX, tileY, height, type, object, orientation);
            } catch(Exception _ex) {
               misc.println("[ERROR]: loading worldobject(" + i + ") failed.");
               _ex.printStackTrace();
               continue;
            }

         }
      }
   }

// getNumberValue() by Phate/WinterLove - came from database.java, renamed & edit by WhiteFang 
   public static int getNumberValue(Element ele, String tagName) {
      try{
         int areturn = Integer.parseInt(getTextValue(ele, tagName));
         return areturn;
      } catch(Exception e) {
         return -1;
      }
   }

// getTextValue() by Phate/WinterLove - came from database.java, renamed & edit by WhiteFang 
   public static String getTextValue(Element ele, String tagName) {
      String textVal = null;
      NodeList nl = ele.getElementsByTagName(tagName);
      if(nl != null && nl.getLength() > 0) {
         Element el = (Element) nl.item(0);
         textVal = el.getFirstChild().getNodeValue();
      }
      return textVal;
   }

 //addObject() by Phate/WinterLove, renamed & edit by WhiteFang 
   private static void addObject(int tileX, int tileY, int height, int type, int object, int orientation) {
      objectId[cWorldMap2] = object;
      objectX[cWorldMap2] = tileX;
      objectY[cWorldMap2] = tileY;
      objectHeight[cWorldMap2] = height;
      objectType[cWorldMap2] = type;
      objectFace[cWorldMap2] = orientation;
      cWorldMap2++;
   }

   //NOTE : three dimensional array made my computer cap memory at 4gb and CPU at 98%
   private static int[][] walkableGridHeight0 = new int[5000][11000];
   private static int[][] walkableGridHeight1 = new int[5000][11000];
   private static int[][] walkableGridHeight2 = new int[5000][11000];
   private static int[][] walkableGridHeight3 = new int[5000][11000];
   
   /**
    * @return if the array returns -1, it is NOT walkable
    */
   public static int[][] getWalkableGridAtHeight(int height){
  	 switch(height){
  	 case 0:
  		 return walkableGridHeight0;
  	 case 1:
  		 return walkableGridHeight1;
  	 case 2:
  		 return walkableGridHeight2;
  	 case 3:
  		 return walkableGridHeight3;
  	 }
  	 System.out.println("[Error] - In WorldMap, walkable grid at height "+height+" called.");
  		return null;
   }
   
   public static void generateWalkableGrid(){
  	 for (int i = 0; i < maxObjects; i++) {
  		 try{
  		 if (objectId[i] == -1) continue;
  		 int x = objectX[i];
  		 int y = objectY[i];
  		 if (objectType[i] == 22){ //walkable
  			 getWalkableGridAtHeight(objectHeight[i])[x][y] = i;
  		 }
  		 else{ //non walkable
  			 getWalkableGridAtHeight(objectHeight[i])[x][y] = -1;
  		 }
  			 
  		 }
  		 catch(Exception e){
  			 System.out.println("[Error] : in generateWalkableGrid in WorldMap.");
  			 System.out.println("object coordinates, height : "+objectX[i]+","+objectY[i]+", h:"+objectHeight[i]);
  		 }
  	 }
   }

   public static boolean isWalkAble(int height, int absX, int absY, int toAbsX, int toAbsY) {
  	 if (absX == toAbsX && absY == toAbsY) return true;
  	 int direction1 = getDirection(absX, absY, toAbsX, toAbsY);
  	 int direction2 = getOppositeDirection(absX, absY, toAbsX, toAbsY);
  	 if (direction1 == -1 || direction2 == -1) {
  		 misc.println("Direction Bug from(" + absX + "," + absY + ") to (" + toAbsX + "," + toAbsY + ") Height(" + height + ") abs(" + toAbsX + "," + toAbsY + ")");
  		 return false;
  	 }
  	 return(getWalkableGridAtHeight(height)[toAbsX][toAbsY] != -1);
   }
   
//   public static boolean isWalkAble(int height, int absX, int absY, int toAbsX, int toAbsY) {
//  	 if (absX == toAbsX && absY == toAbsY) return true;
//  	 int direction1 = getDirection(absX, absY, toAbsX, toAbsY);
//  	 int direction2 = getOppositeDirection(absX, absY, toAbsX, toAbsY);
//  	 if (direction1 == -1 || direction2 == -1) {
//  		 misc.println("Direction Bug from(" + absX + "," + absY + ") to (" + toAbsX + "," + toAbsY + ") Height(" + height + ") abs(" + toAbsX + "," + toAbsY + ")");
//  		 return false;
//  	 }
//  	 for (int i = 0; i < maxObjects; i++) {
//  		 if (objectId[i] == -1) continue;
//  		 if (objectHeight[i] != height) continue;
//  		 if (objectType[i] == 22) continue;
//  		 if (objectX[i] == toAbsX && objectY[i] == toAbsY && height == objectHeight[i]) 
//  			 return false;
//
//  	 }
//  	 return true;
//   }

   public static int getOppositeDirection(int absX, int absY, int toAbsX, int toAbsY) {
// we must reverse direction, walking north will require object to stand south
      if ((absX - 1) == toAbsX && absY == toAbsY) return 2;
      if ((absX + 1) == toAbsX && absY == toAbsY) return 0;
      if (absX == toAbsX && (absY - 1) == toAbsY) return 1;
      if (absX == toAbsX && (absY + 1) == toAbsY) return 3;
      if ((absX - 1) == toAbsX && (absY - 1) == toAbsY) return 11;
      if ((absX + 1) == toAbsX && (absY - 1) == toAbsY) return 10;
      if ((absX - 1) == toAbsX && (absY + 1) == toAbsY) return 8;
      if ((absX + 1) == toAbsX && (absY + 1) == toAbsY) return 9;
	  if (absY == toAbsY && (absX - 1) == toAbsX) return 12;
      if (absY == toAbsY && (absX + 1) == toAbsX) return 13;
      return -1;
   }

   public static int getDirection(int absX, int absY, int toAbsX, int toAbsY) {
// we must reverse direction, walking north will require object to stand south
      if ((absX - 1) == toAbsX && absY == toAbsY) return 0;
      if ((absX + 1) == toAbsX && absY == toAbsY) return 2;
      if (absX == toAbsX && (absY - 1) == toAbsY) return 3;
      if (absX == toAbsX && (absY + 1) == toAbsY) return 1;
      if ((absX - 1) == toAbsX && (absY - 1) == toAbsY) return 9;
      if ((absX + 1) == toAbsX && (absY - 1) == toAbsY) return 8;
      if ((absX - 1) == toAbsX && (absY + 1) == toAbsY) return 10;
      if ((absX + 1) == toAbsX && (absY + 1) == toAbsY) return 11;
	  if (absY == toAbsY && (absX - 1) == toAbsX) return 12;
      if (absY == toAbsY && (absX + 1) == toAbsX) return 13;
      return -1;
   }
}