import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
 
public class ByteCamp03 {
    static List<LinkedList<Integer>> ways = new LinkedList<>();

	public int Wall(int []arr,int n) {
        LinkedList<Integer> temp = new LinkedList<>();
		if(n==1||n==2) {
            temp.add(arr[n-1]);
            ways.add(temp);
            return arr[n-1]; 
        }
		else if (n==3) {
            temp.add(arr[0]);
            temp.add(arr[n-1]);
            ways.add(temp);
            temp = new LinkedList<>();
            temp.add(arr[0]);
            temp.add(arr[1]);
            ways.add(temp);
			return arr[0]+arr[1]+arr[n-1];
		}
		else {
            int min1 = arr[n-1]+arr[n-2]+2*arr[0];
            int min2 = arr[n-1]+arr[1]*2+arr[0];
            if (min1 < min2){
                temp.add(arr[0]);
                temp.add(arr[n-1]);
                ways.add(temp);
                temp = new LinkedList<>();
                temp.add(arr[0]);
                ways.add(temp);
                temp = new LinkedList<>();
                temp.add(arr[0]);
                temp.add(arr[n-2]);
                ways.add(temp);
                temp = new LinkedList<>();
                temp.add(arr[0]);
                ways.add(temp);
			    return Wall(arr, n-2) + min1;
            } else {
                temp.add(arr[0]);
                temp.add(arr[1]);
                ways.add(temp);
                temp = new LinkedList<>();
                temp.add(arr[0]);
                ways.add(temp);
                temp = new LinkedList<>();
                temp.add(arr[n-1]);
                temp.add(arr[n-2]);
                ways.add(temp);
                temp = new LinkedList<>();
                temp.add(arr[1]);
                ways.add(temp);
			    return Wall(arr, n-2) + min2;
            }
		}
	}
	public static void main(String[] args) {
		Scanner scanner=new Scanner(System.in);
		int count=scanner.nextInt();
		int []arr=new int[count];
		int j=0;
		while(count>0) {
			arr[j]=scanner.nextInt();
			count-=1;
			j++;
		}
        scanner.close();
		Arrays.sort(arr);
		ByteCamp03 ans = new ByteCamp03();
        ways = new LinkedList<>();
		System.out.println(ans.Wall(arr, arr.length));
        for (int i = 0; i < ways.size(); i++) {
            for (Integer element : ways.get(i)) {
                System.out.print(element + " ");
            }
            System.out.println();
        }
	}
 
}