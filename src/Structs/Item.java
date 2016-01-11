
import java.io.*;
public class Item {

	public static BST capes = new BST(9004,13304,13305,11341,11342,9099,12225,13001,15054,14644,14073,13340,15119,15150,10146,15721,14235,14074, 14077, 14080, 14083, 14095, 14086, 14089, 14092,14098,14101,14104,14107,14110,14113,14116,14119,14122,14125,14128,14131,14134,14137,14139,14561,14563,10865,10866,10412,10385,11345,10704,10705,10707,10708,10710,10711,10713,10714,10716,10717,10719,10720,10722,10723,10725,10726,10728,10729,10731,10732,10734,10735,10737,10738,10740,10741,10743,10744,10746,10747,10749,10750,10752,10753,10755,10756,10758,10759,10761,10762,10764,10765,10767,10768,10770,11192,11193,11194,7685,7682,7674,7676,7678,7680,7672,7653,7655,7657,7648,7650,7628,7630,7632,7634,7636,7638,7640,7626,7623,7535,3759,3761,3763,3765,3777,3779,3781,3783,3785,3787,3789,4041,4042,6111,1007,1019,1021,1023,1027,1029,1031,1052,2412,2413,2414,4304,4315,4317,4319,4321,4323,4325,4327,4329,4331,4333,4335,4337,4339,4341,4343,4345,4347,4349,4351,4353,4355,4357,4359,4361,4363,4365,4367,4369,4371,4373,4375,4377,4379,4381,4383,4385,4387,4389,4391,4393,4395,4397,4399,4401,4403,4405,4407,4409,4411,4413,4514,4516,6070,6568,6570);

	public static BST boots = new BST(13312,15350,15072,15122,15341,10149,15103,15352,11269,10411,10384,10336,10337,11570,11979,11981,7700,7666,7664,6666,6790,6377,6367,6357,3393,3700,5064,7159,6619,7114,6328,6920,6349,7596,6106,88,89,626,628,630,632,634,1061,1837,1846,2577,2579,2894,2904,2914,2924,2934,3061,3105,3107,3791,4097,4107,4117,4119,4121,4123,4125,4127,4129,4131,4310,5345,5557,6069,6143,6145,6147);

	public static BST gloves = new BST(13313,14505,12003,15123,15342,10147,15106,15113,15237,14506,14530,14526,10410,10383,11137,10225,11161,11153,11157,7537,7671,7453,7454,7455,7456,7457,7458,7459,7460,7461,7462,6720,6379,6369,6359,1580,6068,3391,6629,6330,6922,7595,2491,2487,2489,1495,775,776,777,778,6708,1059,1063,1065,2902,2912,2922,2932,2942,3060,3799,4095,4105,4115,4308,5556,6110,6149,6151,6153);

	public static BST shields = new BST(13314,13361,3627,3629,3631,3633,3635,3637,12000,10145,15340,13603,9115,15104,15116,9114,9116,13602,10121,14514,13601,11814,10232,11145,7692,7625,7643,7676,6788,6789,6889,2997,7332,7334,7336,7338,7340,7342,7344,7346,7348,7350,7352,7354,7356,7358,7360,6631,6633,7053,1171,1173,1175,1177,1179,1181,1183,1185,1187,1189,1191,1193,1195,1197,1199,1201,1540,2589,2597,2603,2611,2621,2629,2659,2667,2675,2890,3122,3488,3758,3839,3840,3841,3842,3843,3844,4072,4156,4224,4225,4226,4227,4228,4229,4230,4231,4232,4233,4234,4507,4512,6215,6217,6219,6221,6223,6225,6227,6229,6231,6233,6235,6237,6239,6241,6243,6245,6247,6249,6251,6253,6255,6257,6259,6261,6263,6265,6267,6269,6271,6273,6275,6277,6279,6524);

	public static BST hats = new BST(11718,11663,11664,11665,9098,13309,13306,13307,14499,14600,10091,10097,15816,15217,15068,15069,15070,9121,15121,15120,9117,15343,15109,15111,15824,15201,9091,10120,15195,14117,14140,13000,14135,14132,14129,14126,14120,14123,14075,14078,14081,14084,14087,14090,14093,14096,14099,14105,14108,14102,14111,14114,14119,14122,14125,14128,14134,14138,14236,15345,14860,14513,15309,14533,14529,14509,10928,10771,10867,11298,11317,10417,10379,10380,10622,10307,11974,11577,11490,10706,10709,10712,10715,10718,10721,10724,10727,10730,10733,10736,10739,10742,10745,10748,10751,10754,10757,10760,10763,10766,10769,11144,11140,11136,10271,11949,11195,11196,11197,11164,11156,11160,11824,11265,11266,11267,11264,7697,7694,7691,7693,7687,7686,7683,7681,7679,7677,7675,7673,7645,7646,7647,7665,7663,7652,7658,7656,7654,7651,7649,7641,7637,7635,7633,7631,7629,7627,7624,7621,1025,1167,1169,7003,4502,6665,1506,6548,6547,7319,7321,7323,7325,7327,6885,6886,6887,2645,2647,2649,7534,7539,3327,3329,3331,3333,3335,3337,3339,3341,3343,6621,6623,7394,7396,7112,7124,7130,7136,7594,6856,6858,6860,6862,6326,7400,6656,4856,4857,4858,4859,4880,4881,4882,4883,4904,4905,4906,4907,4928,4929,4930,4931,4952,4953,4954,4955,4976,4977,4978,4979,4732,4753,4611,6188,6182,4511,4056,4724,2639,2641,2643,2665,6109,5525,5527,5529,5531,5533,5535,5537,5539,5541,5543,5545,5547,5549,5551,74,579,656,658,660,662,664,740,1017,1037,1038,1040,1042,1044,1046,1048,1050,1053,1055,1057,1137,1139,1141,1143,1145,1147,1149,1151,1153,1155,1157,1159,1161,1163,1165,1949,2422,2581,2587,2595,2605,2613,2619,2627,2631,2633,2635,2637,2651,2657,2673,2900,2910,2920,2930,2940,2978,2979,2980,2981,2982,2983,2984,2985,2986,2987,2988,2989,2990,2991,2992,2993,2994,2995,3057,3385,3486,3748,3749,3751,3753,3755,3797,4041,4042,4071,4089,4099,4109,4302,4506,4513,4515,4551,4567,4708,4716,4745,5013,5014,5574,6128,6131,6137,6335,6337,6339,6345,6355,6365,6375,6382,6392,6400,6918);

	public static BST amulets = new BST(9101,9102,5607,15723,15823,15818,15801,5554,4164,14510,14520,11444,11204,11205,11206,10413,10519,11151,11150,11141,6707,6544,5521,4306,3853,3855,3857,3859,3861,3863,3865,3867,2406,1664,1662,1660,1658,1656,1009,774,616,1796,6581,6577,1716,1722,1718,1724,6857,6859,6861,6863,7803,6585,86,87,295,421,552,589,1478,1692,1694,1696,1698,1700,1702,1704,1706,1708,1710,1712,1725,1727,1729,1731,4021,4081,4250,4677,6040,6041,6208);

	public static BST arrows = new BST(4773,4778,4783,4788,4793,4798,4803,78,598,877,878,879,880,881,882,883,884,885,886,887,888,889,890,891,892,893,942,2532,2533,2534,2535,2536,2537,2538,2539,2540,2541,2866,4160,4172,4173,4174,4175,4740,5616,5617,5618,5619,5620,5621,5622,5623,5624,5625,5626,5627,6061,6062);

	public static BST rings = new BST(6738,10415,7688,6731,6733,6735,6737,7927,6583,6575,6347,773,1635,1637,1639,1641,1643,1645,2550,2552,2554,2556,2558,2560,2562,2564,2566,2568,2570,2572,4202,4657,6465);

	public static BST body = new BST(8839,9096,13315,14497,13301,12001,12224,12223,15028,15045,10124,15215,15066,13337,15338,10143,15105,15114,10122,15235,13591,14567,14568,14569,14503,14531,14527,14507,15348,15346,14651,14638,14512,11134,10927,11281,10408,10381,10300,11997,11998,11975,11575,11143,11138,11169,11319,10222,11977,11198,11199,11200,11154,11158,11268,11162,7661,7698,7695,7689,7684,7669,7667,7660,7642,5024,4988,4989,4990,4991,4940,4941,4942,4943,4868,4869,4870,4871,2405,1757,1005,6786,6750,6065,430,75,6371,6361,6351,6341,581,3793,577,546,544,5026,5028,6394,6402,426,5030,5032,5034,3767,3769,3771,3773,3775,6617,6615,7134,7110,7122,7128,1131,1133,1129,6322,7362,7364,2896,2906,2916,2926,2936,1844,636,638,640,642,644,7592,6129,4298,1135,2499,6654,7374,7376,7370,7372,6139,2503,7399,7390,7392,5575,6916,1035,540,5553,4757,1833,6388,6384,2501,1355,4111,4101,4091,6186,6184,6180,3058,4509,4504,4069,4728,4736,4712,6107,2661,3140,1101,1103,1105,1107,1109,1111,1113,1115,1117,1119,1121,1123,1125,1127,2583,2591,2599,2607,2615,2623,2653,2669,3387,3481,4720,4749,4892,4893,4894,4895,4916,4917,4918,4919,4964,4965,4966,4967,6133);

	public static BST legs = new BST(8840,9097,13311,14501,13302,12002,15041,15027,15046,10125,15216,15067,15339,15110,10144,15107,15112,15236,10119,15349,14570,14571,14572,14504,14532,14528,14508,15347,14511,10926,11272,10409,10382,10331,11976,11576,11142,11139,11135,11170,10223,11978,11201,11202,11203,11155,11159,11163,7699,7696,7690,7670,7662,7659,7668,5042,5044,5046,5040,5038,5036,6787,6752,6067,6353,6363,6373,6343,548,428,542,6625,6627,7116,7126,7132,7138,1095,1097,6324,7366,7368,2898,2908,2918,2928,2938,1845,646,648,650,652,654,7593,4300,1835,6655,1033,6141,6135,7382,7384,7378,7380,5555,7386,7388,7398,4759,6386,2497,2495,2493,1099,4113,4103,4093,6924,6187,6185,6181,4510,4505,4070,6108,538,1011,1013,1015,1067,1069,1071,1073,1075,1077,1079,1081,1083,1085,1087,1089,1091,1093,2585,2593,2601,2609,2617,2625,2655,2663,2671,3059,3389,3472,3473,3474,3475,3476,3477,3478,3479,3480,3483,3485,3795,4087,4585,4712,4714,4722,4730,4738,4751,4874,4875,4876,4877,4898,4899,4900,4901,4922,4923,4924,4925,4946,4947,4948,4949,4970,4971,4972,4973,4994,4995,4996,4997,5048,5050,5052,5576,6107,6130,6390,6396,6404,6809);

	public static BST platebody = new BST(8839,9096,13315,14497,13301,12001,12224,12223,15028,15215,15066,13337,15338,10143,15105,15114,15235,10122,13591,14567,14568,14569,14503,14531,14527,14523,14507,15348,15346,14651,14638,14512,11134,10927,11281,10408,10381,10300,11998,11997,11975,11575,11143,11138,11169,11319,10222,11977,11198,11199,11200,11154,11158,11268,11162,7698,7695,7689,7684,7669,7660,7661,7667,7642,6065,5028,5026,5024,2405,6786,6750,75,6371,6361,6351,6341,3793,577,581,546,544,426,3767,3769,3771,3773,3775,6617,2896,2906,2916,2926,2936,1844,636,638,640,642,644,5575,6129,6139,6133,4298,7399,6916,7390,7392,5032,5034,5030,1035,540,5553,4757,1833,1835,6388,6384,1355,4111,4101,4868,4869,4870,4871,4892,4893,4894,4895,4916,4917,4918,4919,4940,4941,4942,4943,4964,4965,4966,4967,4988,4989,4990,4991,4091,6186,6184,6180,3058,4509,4504,4069,4736,4712,6107,3140,1115,1117,1119,1121,1123,1125,1127,2583,2591,2599,2607,2615,2623,2653,2669,3481,4720,4728,4749,2661);

	public static BST fullHelm = new BST(11718,13309,13306,13307,15217,9121,15121,9117,15343,15109,15111,10120,15195,4976,4977,4978,4979,4952,4953,4954,4955,4928,4929,4930,4931,4904,4905,4906,4907,4880,4881,4882,4883,4857,4858,4859,4856,4567,4515,4513,4302,74,3748,6137,6131,6128,6621,1151,1143,1145,1147,1141,1137,1139,1149,4753,4708,4716,7003,7534,4551,4745,6623,5574,7112,7124,7130,7136,7594,6326,4732,6188,4511,4506,4071,4724,6109,2665,1153,1155,1157,1159,1161,1163,1165,2587,2595,2605,2613,2619,2627,2657,2673,3486,6402,6394);

	public static BST fullMask = new BST(11663,11664,11665,13309,13306,13307,15217,9121,15121,9117,15343,15109,15111,10120,15195,14117,14140,13000,14135,14132,14129,14126,14120,14123,14075,14078,14081,14084,14087,14090,14093,14096,14099,14105,14108,14102,14111,14114,14119,14122,14125,14128,14134,14138,14236,10928,10771,10867,11298,11317,10407,10622,11974,11577,11490,10706,10709,10712,10715,10718,10721,10724,10727,10730,10733,10736,10739,10742,10745,10748,10751,10754,10757,10760,10763,10766,10769,11144,11140,11136,11949,11164,11156,11160,11824,7539,7694,7691,7687,7686,7683,7681,7679,7675,7677,7673,7637,7624,7665,7663,7658,7656,7654,7651,7649,7641,7633,7635,7647,7639,7631,7629,7627,7652,1167,1169,1506,6326,4732,4708,4724,4716,4611,6188,3507,4511,4056,4071,2665,6109,1053,1055,1057);

	//All other IDs are weapons (I hope)

	public static int smithing_frame[][][] = {
		{{1205,1, 1,1,1125,1094},{1351,1, 1,1,1126,1091},{1422,1, 2,1,1129,1093},{1139,1, 3,1,1127,1102},{1277,1, 3,1,1128,1085},{819,10, 4,1,1124,1107},{4819,15, 4,1,13357,13358},{39,15, 5,1,1130,1108},{1321,1, 5,2,1116,1087},{1291,1, 6,2,1089,1086},{1155,1, 7,2,1113,1103},{864,5, 7,1,1131,1106},{1173,1, 8,2,1114,1104},{1337,1, 9,3,1118,1083},{1375,1,10,3,1095,1092},{1103,1,11,3,1109,1098},{1189,1,12,3,1115,1105},{3095,1,13,2,8428,8429},{1307,1,14,3,1090,1088},{1087,1,16,3,1111,1100},{1075,1,16,3,1110,1099},{1117,1,18,5,1112,1101},/*Specials*/{1794,1, 4,1, 1132, 1096}},
		{{1203,1,15,1,1125,1094},{1349,1,16,1,1126,1091},{1420,1,17,1,1129,1093},{1137,1,18,1,1127,1102},{1279,1,19,1,1128,1085},{820,10,19,1,1124,1107},{4820,15,19,1,13357,13358},{40,15,20,1,1130,1108},{1323,1,20,2,1116,1087},{1293,1,21,2,1089,1086},{1153,1,22,2,1113,1103},{863,5,22,1,1131,1106},{1175,1,23,2,1114,1104},{1335,1,24,3,1118,1083},{1363,1,25,3,1095,1092},{1101,1,26,3,1109,1098},{1191,1,27,3,1115,1105},{3096,1,28,2,8428,8429},{1309,1,29,3,1090,1088},{1081,1,31,3,1111,1100},{1067,1,31,3,1110,1099},{1115,1,33,5,1112,1101},/*Specials*/{4540,1,26,1,11459,11461}},
		{{1207,1,30,1,1125,1094},{1353,1,31,1,1126,1091},{1424,1,32,1,1129,1093},{1141,1,33,1,1127,1102},{1281,1,34,1,1128,1085},{821,10,34,1,1124,1107},{1539,15,34,1,13357,13358},{41,15,35,1,1130,1108},{1325,1,35,2,1116,1087},{1295,1,36,2,1089,1086},{1157,1,37,2,1113,1103},{865,5,37,1,1131,1106},{1177,1,38,2,1114,1104},{1339,1,39,3,1118,1083},{1365,1,40,3,1095,1092},{1105,1,41,3,1109,1098},{1193,1,42,3,1115,1105},{3097,1,43,2,8428,8429},{1311,1,44,3,1090,1088},{1083,1,46,3,1111,1100},{1069,1,46,3,1110,1099},{1119,1,48,5,1112,1101},/*Specials*/{4544,1,49,1,11459,11461},{2370,1,36,1,1135,1134}},
		{{1209,1,50,1,1125,1094},{1355,1,51,1,1126,1091},{1428,1,52,1,1129,1093},{1143,1,53,1,1127,1102},{1285,1,53,1,1128,1085},{822,10,54,1,1124,1107},{4822,15,54,1,13357,13358},{42,15,55,1,1130,1108},{1329,1,55,2,1116,1087},{1299,1,56,2,1089,1086},{1159,1,57,2,1113,1103},{866,5,57,1,1131,1106},{1181,1,58,2,1114,1104},{1343,1,59,3,1118,1083},{1369,1,60,3,1095,1092},{1109,1,61,3,1109,1098},{1197,1,62,3,1115,1105},{3099,1,63,2,8428,8429},{1315,1,64,3,1090,1088},{1085,1,66,3,1111,1100},{1071,1,66,3,1110,1099},{1121,1,68,5,1112,1101}},
		{{1211,1,70,1,1125,1094},{1357,1,71,1,1126,1091},{1430,1,72,1,1129,1093},{1145,1,73,1,1127,1102},{1287,1,74,1,1128,1085},{823,10,74,1,1124,1107},{4823,15,74,1,13357,13358},{43,15,75,1,1130,1108},{1331,1,75,2,1116,1087},{1301,1,76,2,1089,1086},{1161,1,77,2,1113,1103},{867,5,77,1,1131,1106},{1183,1,78,2,1114,1104},{1345,1,79,3,1118,1083},{1371,1,80,3,1095,1092},{1111,1,81,3,1109,1098},{1199,1,82,3,1115,1105},{3100,1,83,2,8428,8429},{1317,1,84,3,1090,1088},{1091,1,86,3,1111,1100},{1073,1,86,3,1110,1099},{1123,1,88,5,1112,1101}},
		{{1213,1,85,1,1125,1094},{1359,1,86,1,1126,1091},{1432,1,87,1,1129,1093},{1147,1,88,1,1127,1102},{1289,1,89,1,1128,1085},{824,10,89,1,1124,1107},{4824,15,89,1,13357,13358},{44,15,90,1,1130,1108},{1333,1,90,2,1116,1087},{1303,1,91,2,1089,1086},{1163,1,92,2,1113,1103},{868,5,92,1,1131,1106},{1185,1,93,2,1114,1104},{1347,1,94,3,1118,1083},{1373,1,95,3,1095,1092},{1113,1,96,3,1109,1098},{1201,1,97,3,1115,1105},{3101,1,98,2,8428,8429},{1319,1,99,3,1090,1088},{1093,1,99,3,1111,1100},{1079,1,99,3,1110,1099},{1127,1,99,5,1112,1101}}
		//			0			1			2			3			4			5			6				7			8			9			10			11			12			13			14			15			16		17				18			19			20			21				22			23
		//			dagger			axe			mace			medium			sword			dart tips		nails				arrow heads		scimitar		long sword		full helmet		knives			square			warhammer		battle axe		chain			kite		claws				2-handed		skirt			legs			body				lantern/wire		studs
	};
	public static int SmithingItems[][] = new int[5][5];

	static final int ANTIDRAGONFIRESHIELD = 1540;
	static final int DFS = 13361;
	static final int CRYSTALBOW = 4212;
	static final int DARKBOW = 15156;
	static final int KARILSCROSSBOW = 4734;
	static final int ARMADYL_GODSWORD = 15333;
	static final int BANDOS_GODSWORD = 15334;
	static final int SARADOMIN_GODSWORD = 15335;
	static final int ZAMORAK_GODSWORD = 15336;
	static final int ACCURATE = -100;
	static final int RAPID = -200;
	static final int LONGRANGE = -300;
	static final int MAGIC = -400;
	
	/**
	 * Gets the delay for the item passed in
	 * @return Weapon delay to be used with combat, 3.2 seconds by default
	 */
	public static int getItemDelay(int itemID){
		double rawDelay = getItemDelayInSeconds(itemID);
		return (int)rawDelay*10;
	}
	
	/**
	 * Helper method for getItemDelay
	 */
	private static double getItemDelayInSeconds(int itemID){
		switch(itemID){
		case RAPID: case 4151: case 11337: //abby whip, dragon claws
			return 2.4;
		case ARMADYL_GODSWORD: case BANDOS_GODSWORD: case SARADOMIN_GODSWORD: case ZAMORAK_GODSWORD:
			return 3.6;	
		case ACCURATE:
			return 3.5;
		case MAGIC:
			return 3.0;
		case LONGRANGE: default:
			return 4.0;
		}
	}
	
	/**
	 * Checks to see if the player has a bow and ammo equipped
	 * Will modify PkingDelay if a bow is equipped
	 * @return The distance of what they have equipped, depending on type of fight style.
	 * Returns -1 if a bow is equipped with no ammo.
	 * Returns 1 as default if no bow is equipped.
	 */
	public static int ifHasBowAndAmmoUpdateDelay(client c){
		if(lists.bows.exists(c.playerEquipment[c.playerWeapon])){ //Check to see if a bow is equipped
			if(!c.BOWHANDLER.checkAmmo())
				return -1;
			switch (c.FightType) {
				case 1:
					c.PkingDelay = getItemDelay(ACCURATE); 
					return 4;
				case 2: //rapid
					c.PkingDelay = getItemDelay(RAPID); 
					return 3;
				case 3:
					c.PkingDelay = getItemDelay(LONGRANGE); 
					return 6;
			}		
		}
		return 1; //default distance, no bow equipped
	}
	
	public static boolean[] itemStackable = new boolean[17000];
	public static boolean[] itemIsNote = new boolean[17000];
	public static boolean[] itemTwoHanded = new boolean[17000];
	public static boolean[] itemTradeable = new boolean[17000];
	public static boolean[] itemSellable = new boolean[17000];
	static {
		int counter = 0;
		int c;
		try {
			FileInputStream dataIn = new FileInputStream(new File("data/stackable.dat"));
			while ((c = dataIn.read()) != -1) {
				if (c == 0)
					itemStackable[counter] = false;
				else
					itemStackable[counter] = true;
				counter++;
			}
			dataIn.close();
			itemStackable[13303] = true;
			itemStackable[892] = true;
			itemStackable[6570] = false;
			itemStackable[6731] = false;
			itemStackable[6733] = false;
			itemStackable[6735] = false;
			itemStackable[6737] = false;
			itemStackable[6739] = false;
			itemStackable[6745] = false;
			itemStackable[6746] = false;
			itemStackable[6568] = false;
			itemStackable[6666] = false;
			itemStackable[6665] = false;
		} catch (IOException e) {
			System.out.println("Critical error while loading stackabledata! Trace:");
			e.printStackTrace();
		}

		counter = 0;
		try {
			FileInputStream dataIn = new FileInputStream(new File("data/notes.dat"));
			while ((c = dataIn.read()) != -1) {
				if (c == 0)
					itemIsNote[counter] = true;
				else
					itemIsNote[counter] = false;
				counter++;
			}
			dataIn.close();
		} catch (IOException e) {
			System.out.println("Critical error while loading notedata! Trace:");
			e.printStackTrace();
		}

		counter = 0;
		try {
			FileInputStream dataIn = new FileInputStream(new File("data/twohanded.dat"));
			while ((c = dataIn.read()) != -1) {
				if (c == 0)
					itemTwoHanded[counter] = false;
				else
					itemTwoHanded[counter] = true;
				counter++;
			}
			dataIn.close();
		} catch (IOException e) {
			System.out.println("Critical error while loading twohanded! Trace:");
			e.printStackTrace();
		}

		counter = 0;		
		try {
			FileInputStream dataIn = new FileInputStream(new File("data/tradeable.dat"));
			while ((c = dataIn.read()) != -1) {
				if (c == 0)
					itemTradeable[counter] = false;
				else
					itemTradeable[counter] = true;
				counter++;
			}
			dataIn.close();
		} catch (IOException e) {
			System.out.println("Critical error while loading tradeable! Trace:");
			e.printStackTrace();
		}

		counter = 0;		
		try {
			FileInputStream dataIn = new FileInputStream(new File("data/sellable.dat"));
			while ((c = dataIn.read()) != -1) {
				if (c == 0)
					itemSellable[counter] = true;
				else
					itemSellable[counter] = false;
				counter++;
			}
			dataIn.close();
		} catch (IOException e) {
			System.out.println("Critical error while loading sellable! Trace:");
			e.printStackTrace();
		}
	}	

	public static void capeEmote(client c) {

		if (c.playerEquipment[c.playerCape] == 14074) { //attack
			c.stillgfx(480, c.absY, c.absX);
			c.stillgfx(484, c.absY, c.absX -1);
			c.stillgfx(484, c.absY, c.absX +1);
			c.stillgfx(484, c.absY -1, c.absX);
			c.stillgfx(484, c.absY +1, c.absX);
			c.stillgfx(484, c.absY +1, c.absX +1);
			c.stillgfx(484, c.absY -1, c.absX -1);
			c.stillgfx(484, c.absY +1, c.absX -1);
			c.stillgfx(484, c.absY -1, c.absX +1);
		}

		if (c.playerEquipment[c.playerCape] == 14073) { //attack
			c.startAnimation(1670);
			c.stillgfx(60, c.absY, c.absX -1);
			c.stillgfx(287, c.absY, c.absX);
			c.stillgfx(60, c.absY, c.absX +1);
			c.stillgfx(60, c.absY -1, c.absX);
			c.stillgfx(60, c.absY +1, c.absX);
			c.stillgfx(84, c.absY, c.absX);
			c.stillgfx(263, c.absY, c.absX);
			c.stillgfx(264, c.absY, c.absX);
			c.stillgfx(426, c.absY, c.absX);
			c.stillgfx(199, c.absY, c.absX -1);
			c.stillgfx(199, c.absY, c.absX +1);
			c.stillgfx(199, c.absY -1, c.absX);
			c.stillgfx(199, c.absY +1, c.absX);

		}

		if (c.playerEquipment[c.playerCape] == 14077) { //strength
			c.startAnimation(1914);
			c.stillgfx(76, c.absY, c.absX -1);
			c.stillgfx(76, c.absY, c.absX +1);
			c.stillgfx(76, c.absY -1, c.absX);
			c.stillgfx(76, c.absY +1, c.absX);
		}
		if (c.playerEquipment[c.playerCape] == 14080) { //defence
			c.startAnimation(403);
			c.stillgfx(1, c.absY, c.absX -1);
			c.stillgfx(1, c.absY, c.absX +1);
			c.stillgfx(1, c.absY -1, c.absX);
			c.stillgfx(1, c.absY +1, c.absX);
		}
		if (c.playerEquipment[c.playerCape] == 14095) { //hitpoints
			c.startAnimation(1914);
			c.stillgfx(444, c.absY, c.absX);
			c.stillgfx(574, c.absY, c.absX);
			c.stillgfx(199, c.absY, c.absX);
		}
		if (c.playerEquipment[c.playerCape] == 14083) { //ranging
			c.startAnimation(250);
			c.stillgfx(474, c.absY, c.absX);
			c.stillgfx(474, c.absY, c.absX);
			c.stillgfx(472, c.absY, c.absX -1);
			c.stillgfx(472, c.absY, c.absX +1);
			c.stillgfx(472, c.absY -1, c.absX);
			c.stillgfx(472, c.absY +1, c.absX);
			c.stillgfx(472, c.absY +1, c.absX +1);
			c.stillgfx(472, c.absY -1, c.absX -1);
			c.stillgfx(472, c.absY +1, c.absX -1);
			c.stillgfx(472, c.absY -1, c.absX +1);
		}
		if (c.playerEquipment[c.playerCape] == 14086) { //prayer
			c.stillgfx(520, c.absY, c.absX+1);
			c.stillgfx(520, c.absY, c.absX-1);
			c.stillgfx(520, c.absY-1, c.absX);
			c.stillgfx(520, c.absY+1, c.absX);
			c.stillgfx(263, c.absY, c.absX);
			c.stillgfx(264, c.absY, c.absX);
			c.stillgfx(199, c.absY, c.absX -1);
			c.stillgfx(199, c.absY, c.absX +1);
			c.stillgfx(199, c.absY -1, c.absX);
			c.stillgfx(199, c.absY +1, c.absX);
		}
		if (c.playerEquipment[c.playerCape] == 14089) { //magic
			c.startAnimation(811);
			c.stillgfx(540, c.absY, c.absX -1);
			c.stillgfx(540, c.absY, c.absX +1);
			c.stillgfx(540, c.absY -1, c.absX);
			c.stillgfx(540, c.absY +1, c.absX);
			c.stillgfx(540, c.absY +1, c.absX +1);
			c.stillgfx(540, c.absY -1, c.absX -1);
			c.stillgfx(540, c.absY +1, c.absX -1);
			c.stillgfx(540, c.absY -1, c.absX +1);
		}
		if (c.playerEquipment[c.playerCape] == 15150) { //Hunter

		}
		if (c.playerEquipment[c.playerCape] == 14128) { //cooking
			c.startAnimation(883);
			c.stillgfx(563, c.absY, c.absX -1);
			c.stillgfx(563, c.absY, c.absX +1);
			c.stillgfx(563, c.absY -1, c.absX);
			c.stillgfx(563, c.absY +1, c.absX);
		}
		if (c.playerEquipment[c.playerCape] == 14134) { //woodcutting
			c.startAnimation(875);
			c.stillgfx(83, c.absY, c.absX -1);
			c.stillgfx(83, c.absY, c.absX +1);
			c.stillgfx(83, c.absY -1, c.absX);
			c.stillgfx(83, c.absY +1, c.absX);
		}
		if (c.playerEquipment[c.playerCape] == 14110) { //fletching
			c.stillgfx(588, c.absY, c.absX -1);
			c.stillgfx(588, c.absY, c.absX +1);
			c.stillgfx(588, c.absY -1, c.absX);
			c.stillgfx(588, c.absY +1, c.absX);
		}
		if (c.playerEquipment[c.playerCape] == 14125) { //fish
			c.startAnimation(618);
			c.stillgfx(267, c.absY, c.absX);
			c.stillgfx(176, c.absY, c.absX -1);
			c.stillgfx(176, c.absY, c.absX +1);
			c.stillgfx(176, c.absY -1, c.absX);
			c.stillgfx(176, c.absY +1, c.absX);
		}
		if (c.playerEquipment[c.playerCape] == 14131) { //firemaking
			c.stillgfx(453, c.absY, c.absX -1);
			c.stillgfx(446, c.absY, c.absX);
			c.stillgfx(453, c.absY, c.absX +1);
			c.stillgfx(453, c.absY -1, c.absX);
			c.stillgfx(453, c.absY +1, c.absX);
		}
		if (c.playerEquipment[c.playerCape] == 14107) { //crafting
			c.startAnimation(885);
			c.stillgfx(239, c.absY, c.absX -1);
			c.stillgfx(239, c.absY, c.absX +1);
			c.stillgfx(239, c.absY -1, c.absX);
			c.stillgfx(239, c.absY +1, c.absX);
		}
		if (c.playerEquipment[c.playerCape] == 14122) { //smithing
			c.startAnimation(898);
			c.stillgfx(436, c.absY, c.absX);
		}
		if (c.playerEquipment[c.playerCape] == 14119) { //mining
			c.startAnimation(625);
			c.stillgfx(60, c.absY, c.absX -1);
			c.stillgfx(287, c.absY, c.absX);
			c.stillgfx(60, c.absY, c.absX +1);
			c.stillgfx(60, c.absY -1, c.absX);
			c.stillgfx(60, c.absY +1, c.absX);
		}
		if (c.playerEquipment[c.playerCape] == 14101) { //herblore
			c.startAnimation(1350);
			c.stillgfx(267, c.absY, c.absX);
			c.stillgfx(255, c.absY, c.absX);
			c.stillgfx(259, c.absY, c.absX);
			c.stillgfx(352, c.absY, c.absX -1);
			c.stillgfx(352, c.absY, c.absX +1);
			c.stillgfx(352, c.absY -1, c.absX);
			c.stillgfx(352, c.absY +1, c.absX);
		}
		if (c.playerEquipment[c.playerCape] == 14098) { //agility
			c.startAnimation(1353);
			c.stillgfx(601, c.absY, c.absX);
			c.stillgfx(108, c.absY, c.absX);
		}
		if (c.playerEquipment[c.playerCape] == 14104) { //thieving
			c.startAnimation(2890);
			c.stillgfx(437, c.absY, c.absX);
			c.stillgfx(465, c.absY, c.absX);
		}
		if (c.playerEquipment[c.playerCape] == 14113) { //slayer
			c.startAnimation(2844);
			c.stillgfx(559, c.absY, c.absX -1);
			c.stillgfx(559, c.absY, c.absX +1);
			c.stillgfx(559, c.absY -1, c.absX);
			c.stillgfx(559, c.absY +1, c.absX);
			c.txt4 = "Muhahahaha!";
			c.string4UpdateRequired = true;
		}
		if (c.playerEquipment[c.playerCape] == 14137) { //farming
			c.startAnimation(2273);
			c.stillgfx(474, c.absY, c.absX -1);
			c.stillgfx(474, c.absY, c.absX +1);
			c.stillgfx(474, c.absY -1, c.absX);
			c.stillgfx(474, c.absY +1, c.absX);

		}
		if (c.playerEquipment[c.playerCape] == 14092) { //runecrafting
			c.startAnimation(1329);
			c.stillgfx(451, c.absY, c.absX -1);
			c.stillgfx(451, c.absY, c.absX +1);
			c.stillgfx(451, c.absY -1, c.absX);
			c.stillgfx(451, c.absY +1, c.absX);

			c.stillgfx(481, c.absY, c.absX -1);
			c.stillgfx(481, c.absY, c.absX +1);
			c.stillgfx(481, c.absY -1, c.absX);
			c.stillgfx(481, c.absY +1, c.absX);

			c.stillgfx(560, c.absY, c.absX -3);
			c.stillgfx(560, c.absY, c.absX +3);
			c.stillgfx(560, c.absY -3, c.absX);
			c.stillgfx(560, c.absY +3, c.absX);

		}
		if (c.playerEquipment[c.playerCape] == 14116) { //Construct.
			c.attackPlayersWithin(451, 4000, 100);
			c.attackPlayersWithin(444, 4000, 100);
			c.attackPlayersWithin(341, 4000, 100);
			c.attackPlayersWithin(76, 4000, 100);
			c.stillgfx(255, c.absY, c.absX +1);
			c.stillgfx(255, c.absY, c.absX -1);
			c.stillgfx(255, c.absY -1, c.absX);
			c.stillgfx(255, c.absY +1, c.absX);
			c.stillgfx(255, c.absY +1, c.absX +1);
			c.stillgfx(255, c.absY -1, c.absX -1);
			c.stillgfx(255, c.absY +1, c.absX -1);
			c.stillgfx(255, c.absY -1, c.absX +1);


		}
		if (c.playerEquipment[c.playerCape] == 14139) { //Quest Cape
			c.startAnimation(1818);
			c.stillgfx(187, c.absY, c.absX +1);
			c.stillgfx(187, c.absY, c.absX -1);
			c.stillgfx(187, c.absY -1, c.absX);
			c.stillgfx(187, c.absY +1, c.absX);
			c.stillgfx(187, c.absY +1, c.absX +1);
			c.stillgfx(187, c.absY -1, c.absX -1);
			c.stillgfx(187, c.absY +1, c.absX -1);
			c.stillgfx(187, c.absY -1, c.absX +1);
			c.stillgfx(474, c.absY, c.absX -1);
			c.stillgfx(474, c.absY, c.absX +1);
			c.stillgfx(474, c.absY -1, c.absX);
			c.stillgfx(474, c.absY +1, c.absX);
		}
	}	







}