import java.util.ArrayList;


public class Permutation {

    public static ArrayList<ArrayList<Integer>> permutation(ArrayList<Integer> list) {
        list = new ArrayList<Integer>(list);

        if (list.size() < 1)
            return new ArrayList<ArrayList<Integer>>();
        if (list.size() == 1) {
            ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
            result.add(list);
            return result;
        }

        int firstEntry = list.remove(0);
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        ArrayList<ArrayList<Integer>> in = permutation(list);


        for (int i = 0; i < in.size(); i++) {
            for (int j = 0; j < in.get(i).size() + 1; j++) {
                ArrayList<Integer> arr = new ArrayList<Integer>(in.get(i));
                arr.add(j, firstEntry);
                result.add(arr);
            }
        }

        return result;
    }
}