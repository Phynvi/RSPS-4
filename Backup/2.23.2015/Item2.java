//  This file is free software; you can redistribute it and/or modify it under
//  the terms of the GNU General Public License version 2, 1991 as published by
//  the Free Software Foundation.

//  This program is distributed in the hope that it will be useful, but WITHOUT
//  ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
//  FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more
//  details.

//  A copy of the GNU General Public License can be found at:
//    http://www.gnu.org/licenses/gpl.html

// a collection of item methods
import java.io.*;
public class Item2 
{

//New Drops by AAA Mods

public static int man123[] = {4151,4153};

  public static int randomman123()
  {
  	return man123[(int)(Math.random()*man123.length)];
  }

	//Barrows drops
public static int barrow[] = {13603,4151,1333,4587,1079,1093,1127,2615,2617,2618,2620,2622,3476,1201,1163,2619,15352,3202,3204,1434,1377,5698,13601,2577,6739,4675,4087,1373,1347,4131,1333,4587,1079,1093,1127,2615,2617,2618,2620,2622,3476,1201,1163,2619,15352,3202,3204,1434,1377,5698,13601,2577,6739,4675,4087,1373,1347,4131,1333,4587,1079,1093,1127,2615,2617,2618,2620,2622,3476,1201,1163,2619,15352,3202,3204,1434,1377,5698,13601,2577,6739,4675,4087,1373,1347,2428,2436,1961};

  public static int randombarrow()
  {
  	return barrow[(int)(Math.random()*barrow.length)];
  }
	//Barrows drops



	//Steel, Iron, and Bronze Dragons
public static int dragon1[] = {1149,1187,1215,1249,1305,1377,1434,1540,1712,2366,2368,3204,4087,4585,4587,5698,6739,7158,15195,15352,13602,9093}; 
 public static int randomdragon1() 
 { 
 	return dragon1[(int)(Math.random()*dragon1.length)]; 
 } 
public static int dragon2[] = {1149,1187,1215,1249,1305,1377,1434,1540,1712,2366,2368,3204,4087,4585,4587,5698,6739,7158,15195,15352,13602,9093,3122}; 
 public static int randomdragon2() 
 { 
 	return dragon2[(int)(Math.random()*dragon2.length)]; 
 } 
public static int dragon3[] = {1149,1187,1215,1249,1305,1377,1434,1540,1712,2366,2368,3204,4087,4585,4587,5698,6739,7158,15195,15352,13602,9093,3140,3122}; 
 public static int randomdragon3() 
 { 
 	return dragon3[(int)(Math.random()*dragon3.length)]; 
 } 
	//Steel, Iron, and Bronze Dragons

public static int monk[] = {534}; 
 public static int randommonk() 
 { 
 	return monk[(int)(Math.random()*monk.length)]; 
 } 

public static int training[] = {2599,2607,3474,1161,2605,2613,1199,2603,2611,1067,1081,1115,1153,1191,1069,1083,1119,1157,1193,1725,1731,1704,113,139,121,1183,1177,1179,1173,1123,1091,1073,1323,1325,1327,1329,1331,1333,1279,1281,1283,1285,1287,1289,1293,1295,1297,1299,1301,1303,1309,1311,1313,1315,1317,1319,1363,1365,1367,1369,1371,1373,1127,1079,1201,1163,4131}; 
 public static int randomtraining() 
 { 
 	return training[(int)(Math.random()*training.length)]; 
 } 

public static int abby[] = {4151,4153,1249,5698,3122,3140,3204,2434,3842,6585,6575,6916,6918,6920,6922,6924,6903,4675}; 
 public static int randomabby() 
 { 
 	return abby[(int)(Math.random()*abby.length)]; 
 } 

public static int giants[] = {1079,1127,1201,1163,1289,1303,1319,1333,4587,5698,1305,1149,3840,3842,1215,1249,4131,1377,1434,3122,1712,1725,3204,13601,1323,1325,1327,1329,1331,1333,1279,1281,1283,1285,1287,1289,1293,1295,1297,1299,1301,1303,1309,1311,1313,1315,1317,1319,1363,1365,1367,1369,1371,1373,1127,1079,1201,1163,4131}; 
 public static int randomgiants() 
 { 
 	return giants[(int)(Math.random()*giants.length)]; 
 } 

public static int jadkq[] = {14511,14512,14513,14514,14860,14638,15195,15352,15346,15347,15348,15349,15350,14507,14508,14509,1149,1187,1215,1249,1305,1377,1434,1540,1712,2366,2368,3204,4087,4585,4587,5698,6739,7158,15195,15352,13602,9093,3140,3122,4151,4153,1187,1187,7158,6739,4151}; 
 public static int randomjadkq() 
 { 
 	return jadkq[(int)(Math.random()*jadkq.length)]; 
 } 

public static int jadcape[] = {6570}; 
 public static int randomjadcape() 
 { 
 	return jadcape[(int)(Math.random()*jadcape.length)]; 
 } 

public static int bbones[] = {532}; 
 public static int randombbones() 
 { 
 	return bbones[(int)(Math.random()*bbones.length)]; 
 } 

public static int dbones[] = {536}; 
 public static int randomdbones() 
 { 
 	return dbones[(int)(Math.random()*dbones.length)]; 
 } 
public static int barrows[] = {4131,4708,4710,4712,4714,4716,4718,4720,4722,4724,4726,4728,4730,4732,4734,4736,4738,4745,4747,4749,4751,4753,4755,4757,4759}; 
 public static int randombarrows() 
 { 
 	return barrows[(int)(Math.random()*barrows.length)]; 
 } 

public static int druid[] = {199,199,199,199,199,199,199,199,199,199,199,199,201,201,201,201,201,203,203,203,205,205,207,209,211,213,215,217,219,231,231}; 
 public static int randomdruid() 
 { 
 	return druid[(int)(Math.random()*druid.length)]; 
 } 


public static int slayer1[] = {199,199,199,199,199,201,201,201,201,203,203,203,203,205,205,205,205,207,207,207,209,209,209,211,211,211,213,213,213,215,215,217,217,219,219,1333,1319,1319,1373,1387,1631,1645,1712,1708,1725,1891,2363,2366,2368,2653,2655,2657,2659,2661,2663,2665,2667,2669,2671,2673,2675,3176,5761,5761,4156,6199,6199,6585,7451,1333,1319,1319,1373,1387,1631,1645,1712,1708,1725,1891,2363,2366,2368,2653,2655,2657,2659,2661,2663,2665,2667,2669,2671,2673,2675,3176,5761,5761,4156,6199,6199,6585,7451,4087,4585,1187,2366,2366,2366,2368,2368,2368,2368,2366,5698,1377,7158,3204,1215,1149,536}; 
 public static int randomslayer1() 
 { 
 	return slayer1[(int)(Math.random()*slayer1.length)]; 
 } 
public static int slayer2[] = {1333,1319,1319,1373,1387,1631,1645,1712,1708,1725,1891,2363,2366,2368,2653,2655,2657,2659,2661,2663,2665,2667,2669,2671,2673,2675,3176,5761,5761,4156,6199,6199,6585,7451,6199,6585,7451,4087,4585,1187,2366,2366,2366,2368,2368,2368,2368,2366,5698,1377,7158,3204,1215,1149,536}; 
 public static int randomslayer2() 
 { 
 	return slayer2[(int)(Math.random()*slayer2.length)]; 
 } 
public static int slayer3[] = {6199,6585,7451,4087,4585,1187,2366,2366,2366,2368,2368,2368,2368,2366,5698,1377,7158,3204,1215,1149,536,6199,6585,7451,4087,4585,1187,2366,2366,2366,2368,2368,2368,2368,2366,5698,1377,7158,3204,1215,1149,536,3140,13602,13601,15234}; 
 public static int randomslayer3() 
 { 
 	return slayer3[(int)(Math.random()*slayer3.length)]; 
 } 



//Slayer

//End of New Drops by AAA Mods



public static int barrows1[] = {536};

    public static int random1Barrows()
    {
       return barrows[(int)(Math.random()*barrows.length)];
    }

public static int barrows2[] = {536};

    public static int random2Barrows()
    {
       return barrows[(int)(Math.random()*barrows.length)];
    }

public static int barrows3[] = {536};

    public static int random3Barrows()
    {
       return barrows[(int)(Math.random()*barrows.length)];
    }

public static int barrows4[] = {536};

    public static int random4Barrows()
    {
       return barrows[(int)(Math.random()*barrows.length)];
    }

public static int runerock[] = {536};

    public static int randomRuneRock()
    {
    	return runerock[(int)(Math.random()*runerock.length)];
    }	

public static int KBD[] = {536};

    public static int randomKBD()
    {
    	return KBD[(int)(Math.random()*KBD.length)];
    }	

public static int fish[] = {536};

    public static int randomFish()
    {
    	return fish[(int)(Math.random()*fish.length)];
    }	

public static int abyssal[] = {536};

    public static int randomAbyssal()
    {
    	return abyssal[(int)(Math.random()*abyssal.length)];
    }	

public static int herby[] = {536};

    public static int randomHerby()
    {
    	return herby[(int)(Math.random()*herby.length)];
    }	
public static int bones[] = {536};

    public static int bones()
    {
       return bones[(int)(Math.random()*bones.length)];
    }

public static int bigbones[] = {536};

    public static int bigbones()
    {
       return bigbones[(int)(Math.random()*bigbones.length)];
    }

public static int babydbones[] = {536};

    public static int babydbones()
    {
       return babydbones[(int)(Math.random()*babydbones.length)];
    }

public static int dragonbones[] = {536};

    public static int dragonbones()
    {
       return dragonbones[(int)(Math.random()*dragonbones.length)];
    }

public static int zogrebones[] = {536};

    public static int zogrebones()
    {
       return zogrebones[(int)(Math.random()*zogrebones.length)];
    }

public static int fayrgbones[] = {536};

    public static int fayrgbones()
    {
       return fayrgbones[(int)(Math.random()*fayrgbones.length)];
    }

public static int raurgbones[] = {536};

    public static int raurgbones()
    {
       return raurgbones[(int)(Math.random()*raurgbones.length)];
    }

public static int ourgbones[] = {536};

    public static int ourgbones()
    {
       return ourgbones[(int)(Math.random()*ourgbones.length)];
    }

public static int babyreddrag[] = {536};

    public static int babyreddrag()
    {
       return babyreddrag[(int)(Math.random()*babyreddrag.length)];
    }

public static int redhide[] = {536};

    public static int redhide()
    {
       return redhide[(int)(Math.random()*redhide.length)];
    }

public static int reddragon[] = {536};

    public static int reddragon()
    {
       return reddragon[(int)(Math.random()*reddragon.length)];
    }

public static int mossgiant[] = {536};

    public static int mossgiant()
    {
       return mossgiant[(int)(Math.random()*mossgiant.length)];
    }

public static int mossgiant2[] = {536};

    public static int mossgiant2()
    {
       return mossgiant2[(int)(Math.random()*mossgiant2.length)];
    }

public static int firegiant[] = {536};

    public static int firegiant()
    {
       return firegiant[(int)(Math.random()*firegiant.length)];
    }

public static int bronzedragon[] = {536};

    public static int bronzedragon()
    {
       return bronzedragon[(int)(Math.random()*bronzedragon.length)];
    }

public static int irondragon[] = {536};

    public static int irondragon()
    {
       return irondragon[(int)(Math.random()*irondragon.length)];
    }

public static int steeldragon[] = {536};

    public static int steeldragon()
    {
       return steeldragon[(int)(Math.random()*steeldragon.length)];
    }


}

