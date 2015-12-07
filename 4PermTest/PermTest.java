import java.util.*;

public class PermTest {
	//Takes in array of two composers' x most persistent pieces
	//First x elements are from composer 1, last x elements from composer 2
	//Returns a p value by doing a perm test
	//n is number of random rearrangements
	public static double pValue(double[] comp1, double[] comp2, int n){
		double mean1 = computeMean(comp1);
		double mean2 = computeMean(comp2);
		double diff = Math.abs(mean1 - mean2);
		
		System.out.println(mean1 + " " + mean2 + " " + diff);
		
		double numTimesGreater = 0; 
		
		for (int i = 0; i < n; i++){
			double[] bothComp = combineArrays(comp1, comp2);
			shuffle(bothComp);
			
			int len = bothComp.length;
			
			double[] newComp1 = Arrays.copyOfRange(bothComp, 0, len/2);
			double[] newComp2 = Arrays.copyOfRange(bothComp, len/2, len);
			double newMean1 = computeMean(newComp1);
			double newMean2 = computeMean(newComp2);
			double newDiff = Math.abs(newMean1 - newMean2);
			
//			System.out.println(newMean1 + " " + newMean2 + " " + newDiff);
			
			if (newDiff > diff){
				numTimesGreater++;
			}
		}
		
//		System.out.println(numTimesGreater);
		return numTimesGreater/n;
	}
	
	public static double[] combineArrays(double[] arr1, double[] arr2){
		int totlen = arr1.length + arr2.length;
		double[] newArr = new double[totlen];
		
		for (int i = 0; i < arr1.length; i++){
			newArr[i] = arr1[i];
		}
		for (int i = arr1.length; i < totlen; i++){
			newArr[i] = arr2[i - arr1.length];
		}
		
		return newArr;
	}
	
	public static double computeMean(double[] array){
		double sum = 0;
		for (int i = 0; i < array.length; i++){
			sum += array[i];
		}
		return sum/array.length;
	}
	
	//Uses Fisher and Yates' method to shuffle an array
	public static void shuffle(double[] array){
		Random rnd = new Random();
		for (int i = array.length - 1; i > 0; i--){
	      int index = rnd.nextInt(i + 1);
	      // Simple swap
	      double a = array[index];
	      array[index] = array[i];
	      array[i] = a;
	    }
	}
	
	public static void main(String[] args){
		double[] rank1TP = {0.5607, 0.5324, 0.4702, 0.4334, 0.4334, 0.4648, 0.4648, 0.4648, 0.6024, 0.6024, 0.6024, 0.6991, 0.6991};
		double[] rank21TP = {0.3247, 0.7167, 0.4648, 0.6947, 0.6947, 0.7102, 0.797, 0.7824, 0.2422, 0.6458, 0.1876, 0.4047, 0.4047};
		double[] rank41TP = {1.0337, 0.5810, 0.7543, 0.8948, 0.4190, 0.5200, 0.3693, 1.3286, 0.6947, 0.919, 0.8107, 0.7151, 0.7151};
		double[] rank61TP = {0.6172, 0.5764, 0.8948, 0.4855, 0.2224, 0.5127, 0.4859, 0.6929, 0.6555, 0.9573, 0.9695, 0.4929, 0.4087};
		double[] rank81TP = {0.2948, 0.5594, 0.4554, 0.5256, 0.8698, 0.3953, 1.0717, 0.4784, 0.4929, 0.635, 1.1549, 0.9286, 0.5576};
		
		double[] rank11C = {76, 57, 74, 51, 51, 51, 51, 51, 90, 90, 90, 86, 86};
		double[] rank211C = {59, 104, 51, 93, 93, 121, 67, 81, 53, 101, 92, 46, 46};
		double[] rank411C = {131, 78, 96, 57, 69, 77, 63, 135, 93, 95, 83, 59, 59};
		double[] rank611C = {43, 66, 57, 48, 68, 55, 76, 65, 85, 107, 92, 65, 56};
		double[] rank811C = {55, 58, 58, 80, 123, 60, 115, 77, 65, 71, 131, 69, 119};
		
//		System.out.println(pValue(rank1TP, rank21TP, 100000));
//		System.out.println(pValue(rank1TP, rank41TP, 100000));
//		System.out.println(pValue(rank1TP, rank61TP, 100000));
//		System.out.println(pValue(rank1TP, rank81TP, 100000));
//		System.out.println(pValue(rank21TP, rank41TP, 100000));
//		System.out.println(pValue(rank21TP, rank61TP, 100000));
//		System.out.println(pValue(rank21TP, rank81TP, 100000));
//		System.out.println(pValue(rank41TP, rank61TP, 100000));
//		System.out.println(pValue(rank41TP, rank81TP, 100000));
//		System.out.println(pValue(rank61TP, rank81TP, 100000));
		
//		System.out.println(pValue(rank11C, rank211C, 100000));
//		System.out.println(pValue(rank11C, rank411C, 100000));
//		System.out.println(pValue(rank11C, rank611C, 100000));
//		System.out.println(pValue(rank11C, rank811C, 100000));
//		System.out.println(pValue(rank211C, rank411C, 100000));
//		System.out.println(pValue(rank211C, rank611C, 100000));
//		System.out.println(pValue(rank211C, rank811C, 100000));
//		System.out.println(pValue(rank411C, rank611C, 100000));
//		System.out.println(pValue(rank411C, rank811C, 100000));
//		System.out.println(pValue(rank611C, rank811C, 100000));
		
		double[] rank1p1 = {67.09564164, 95.34244664, 52.74540191, 41.223908, 41.223908, 71.76079172, 71.76079172, 71.76079172, 37.08386239, 37.08386239, 37.08386239, 49.15514981, 49.15514981};
		double[] rank21p1 = {63.46103441, 120.3025502, 71.76079172, 66.58267527, 66.58267527, 32.33690463, 69.28015203, 46.2997227, 48.91617467, 74.7809716, 34.39060665, 57.57927203, 57.57927203};
		double[] rank41p1 = {63.10278564, 108.0968918, 45.91940435, 45.0767743, 19.26530506, 46.16611933, 32.20112959, 64.54914629, 66.58267527, 29.63225192, 60.7715482, 40.54381867, 40.54381867};
		double[] rank61p1 = {36.04139568, 40.6548199, 45.0767743, 20.81348086, 16.32074241, 34.09758215, 53.34224381, 49.64972996, 75.36328201, 77.9238236, 53.11568596, 32.89240874, 56.15715579};
		double[] rank81p1 = {23.71849595, 28.66389635, 45.0864546, 59.80101872, 56.73265787, 43.43451903, 56.1832062, 29.46901414, 32.89240874, 53.95607397, 54.42563495, 66.23138547, 62.58119193};
		
		double[] rank1p2 = {194.6180799, 159.9178315, 156.0515884, 98.7992858, 98.7992858, 145.3949426, 145.3949426, 145.3949426, 170.3562221, 170.3562221, 170.3562221, 149.0910947, 149.0910947};
		double[] rank21p2 = {155.8429298, 220.1477555, 145.3949426, 182.0499887, 182.0499887, 100.7269385, 161.0729819, 133.4210207, 169.3192429, 204.4705364, 73.72364617, 167.066504, 167.066504};
		double[] rank41p2 = {173.3900114, 170.1048899, 153.7614774, 165.8008495, 74.39489762, 139.3263035, 70.45558881, 192.345576, 182.0499887, 132.0394564, 169.1340433, 113.0127763, 113.0127763};
		double[] rank61p2 = {97.28555923, 142.1000507, 165.8008495, 74.34536558, 56.6437834, 148.6510749, 175.0444444, 131.2973513, 160.3501498, 179.385844, 151.9334323, 125.2027158, 174.2625077};
		double[] rank81p2 = {79.21793014, 136.0904085, 140.2104286, 147.4320915, 192.417183, 163.841145, 169.2429426, 143.6310711, 125.2027158, 136.1396034, 159.9330354, 138.1883777, 213.9650843};
		
		double[] rank1p3 = {65.49440305, 101.8025546, 53.64523542, 43.42339433, 43.42339433, 77.34333793, 77.34333793, 77.34333793, 35.41539787, 35.41539787, 35.41539787, 47.70181457, 47.70181457};
		double[] rank21p3 = {66.27956536, 126.0997932, 77.34333793, 66.02249637, 66.02249637, 32.25721752, 71.79280683, 46.30403198, 46.48435666, 78.12792812, 36.70232368, 55.67848717, 55.67848717};
		double[] rank41p3 = {62.80329333, 117.6639203, 45.17259738, 41.31436588, 17.79865545, 47.86590905, 33.70302706, 64.9497957, 66.02249637, 26.7926629, 56.84105142, 40.93123465, 40.93123465};
		double[] rank61p3 = {35.49299752, 37.19287379, 41.31436588, 19.7351179, 16.11434386, 30.32553371, 49.64702755, 50.87704471, 80.92101099, 79.1170984, 54.71845049, 30.49699486, 51.07331908};
		double[] rank81p3 = {22.87149803, 26.30330544, 42.51936439, 58.1414421, 53.14966207, 39.26845838, 56.45801859, 26.52934294, 30.49699486, 55.17198373, 56.82691152, 69.91989951, 58.08093714};
		
		double[] rank1p4 = {42.02037038, 34.51537394, 34.21265669, 44.27853596, 44.27853596, 41.09581077, 41.09581077, 41.09581077, 134.2807633, 134.2807633, 134.2807633, 88.60224171, 88.60224171};
		double[] rank21p4 = {43.1418337, 74.82654738, 41.09581077, 66.67485998, 66.67485998, 58.37378557, 53.58159276, 48.05692154, 31.62181624, 89.70817064, 32.24316384, 29.97806141, 29.97806141};
		double[] rank41p4 = {137.5762509, 34.96500774, 94.65579049, 53.19912581, 31.95051602, 68.26517299, 51.32142558, 83.40549461, 66.67485998, 127.5900253, 32.32545286, 53.96539109, 53.96539109};
		double[] rank61p4 = {76.12667931, 35.30181532, 53.19912581, 36.00886917, 27.05968821, 59.46387077, 34.78158638, 54.40041539, 90.73942566, 55.07908125, 80.61945783, 43.75003405, 34.26897552};
		double[] rank81p4 = {19.22877621, 74.78507264, 40.43460414, 31.35528697, 68.66916286, 32.01321303, 95.85426808, 77.00578691, 43.75003405, 47.79699394, 98.63694471, 33.62360266, 64.3785308};
		
		double[] rank1p5 = {103.3076924, 52.92307697, 86.84615391, 53.23076927, 53.23076927, 61.61538466, 61.61538466, 61.61538466, 119.4615385, 119.4615385, 119.4615385, 77.07692312, 77.07692312};
		double[] rank21p5 = {86.84615391, 122.0769231, 61.61538466, 87.38461545, 87.38461545, 109.6923078, 72.84615389, 79.61538466, 91.92307696, 69.92307698, 60.15384621, 59.84615388, 59.84615388};
		double[] rank41p5 = {107.7692308, 73.00000007, 84.69230777, 74.4615385, 55.30769235, 92.23076928, 56.00000004, 62.46153852, 87.38461545, 65.00000004, 88.07692313, 70.76923083, 70.76923083};
		double[] rank61p5 = {23.15384618, 86.00000004, 74.4615385, 48.4615385, 73.76923083, 71.23076928, 85.07692312, 60.23076929, 68.15384618, 83.30769238, 52.46153851, 71.76923082, 78.1538462};
		double[] rank81p5 = {81.7692308, 79.30769235, 73.15384619, 79.30769234, 80.00000007, 79.7692308, 69.38461545, 96.38461541, 71.76923082, 81.30769236, 88.30769239, 62.84615391, 118.4615385};
		
		double[] rank1p6 = {104.2903226, 70.51612903, 31.7419355, 70.67741937, 70.67741937, 84.83870967, 84.83870967, 84.83870967, 92.74193551, 92.74193551, 92.74193551, 96.29032259, 96.29032259};
		double[] rank21p6 = {26.41935485, 24.51612906, 84.83870967, 23.74193551, 23.74193551, 164.6774193, 36.38709679, 63.32258067, 8.806451626, 48.35483875, 268, 31.67741936, 31.67741936};
		double[] rank41p6 = {134.3225807, 81.83870968, 66.12903229, 45.67741937, 150, 122.8709677, 97.41935483, 93.70967745, 23.74193551, 107.8709677, 31.38709678, 71.96774194, 71.96774194};
		double[] rank61p6 = {41.45161292, 29.48387098, 45.67741937, 63.25806452, 191, 20.80645163, 9.419354846, 38.35483873, 78.58064517, 43.83870971, 73.51612905, 27.25806454, 18.67741936};
		double[] rank81p6 = {147.0967742, 24.51612906, 37.41935485, 23.09677421, 25.80645165, 2.483870979, 41.32258068, 32.70967745, 27.25806454, 59.32258065, 131.516129, 66.03225808, 18.03225808};
		
//		System.out.println(pValue(rank1p1, rank21p1, 100000));
//		System.out.println(pValue(rank1p1, rank41p1, 100000));
//		System.out.println(pValue(rank1p1, rank61p1, 100000));
//		System.out.println(pValue(rank1p1, rank81p1, 100000));
//		System.out.println(pValue(rank21p1, rank41p1, 100000));
//		System.out.println(pValue(rank21p1, rank61p1, 100000));
//		System.out.println(pValue(rank21p1, rank81p1, 100000));
//		System.out.println(pValue(rank41p1, rank61p1, 100000));
//		System.out.println(pValue(rank41p1, rank81p1, 100000));
//		System.out.println(pValue(rank61p1, rank81p1, 100000));
//		System.out.println();
//		System.out.println(pValue(rank1p2, rank21p2, 100000));
//		System.out.println(pValue(rank1p2, rank41p2, 100000));
//		System.out.println(pValue(rank1p2, rank61p2, 100000));
//		System.out.println(pValue(rank1p2, rank81p2, 100000));
//		System.out.println(pValue(rank21p2, rank41p2, 100000));
//		System.out.println(pValue(rank21p2, rank61p2, 100000));
//		System.out.println(pValue(rank21p2, rank81p2, 100000));
//		System.out.println(pValue(rank41p2, rank61p2, 100000));
//		System.out.println(pValue(rank41p2, rank81p2, 100000));
//		System.out.println(pValue(rank61p2, rank81p2, 100000));
//		System.out.println();
//		System.out.println(pValue(rank1p3, rank21p3, 100000));
//		System.out.println(pValue(rank1p3, rank41p3, 100000));
//		System.out.println(pValue(rank1p3, rank61p3, 100000));
//		System.out.println(pValue(rank1p3, rank81p3, 100000));
//		System.out.println(pValue(rank21p3, rank41p3, 100000));
//		System.out.println(pValue(rank21p3, rank61p3, 100000));
//		System.out.println(pValue(rank21p3, rank81p3, 100000));
//		System.out.println(pValue(rank41p3, rank61p3, 100000));
//		System.out.println(pValue(rank41p3, rank81p3, 100000));
//		System.out.println(pValue(rank61p3, rank81p3, 100000));
//		System.out.println();
//		System.out.println(pValue(rank1p4, rank21p4, 100000));
//		System.out.println(pValue(rank1p4, rank41p4, 100000));
//		System.out.println(pValue(rank1p4, rank61p4, 100000));
//		System.out.println(pValue(rank1p4, rank81p4, 100000));
//		System.out.println(pValue(rank21p4, rank41p4, 100000));
//		System.out.println(pValue(rank21p4, rank61p4, 100000));
//		System.out.println(pValue(rank21p4, rank81p4, 100000));
//		System.out.println(pValue(rank41p4, rank61p4, 100000));
//		System.out.println(pValue(rank41p4, rank81p4, 100000));
//		System.out.println(pValue(rank61p4, rank81p4, 100000));
//		System.out.println();
//		System.out.println(pValue(rank1p5, rank21p5, 100000));
//		System.out.println(pValue(rank1p5, rank41p5, 100000));
//		System.out.println(pValue(rank1p5, rank61p5, 100000));
//		System.out.println(pValue(rank1p5, rank81p5, 100000));
//		System.out.println(pValue(rank21p5, rank41p5, 100000));
//		System.out.println(pValue(rank21p5, rank61p5, 100000));
//		System.out.println(pValue(rank21p5, rank81p5, 100000));
//		System.out.println(pValue(rank41p5, rank61p5, 100000));
//		System.out.println(pValue(rank41p5, rank81p5, 100000));
//		System.out.println(pValue(rank61p5, rank81p5, 100000));
//		System.out.println();
//		System.out.println(pValue(rank1p6, rank21p6, 100000));
//		System.out.println(pValue(rank1p6, rank41p6, 100000));
//		System.out.println(pValue(rank1p6, rank61p6, 100000));
//		System.out.println(pValue(rank1p6, rank81p6, 100000));
//		System.out.println(pValue(rank21p6, rank41p6, 100000));
//		System.out.println(pValue(rank21p6, rank61p6, 100000));
//		System.out.println(pValue(rank21p6, rank81p6, 100000));
//		System.out.println(pValue(rank41p6, rank61p6, 100000));
//		System.out.println(pValue(rank41p6, rank81p6, 100000));
//		System.out.println(pValue(rank61p6, rank81p6, 100000));
	}
}
