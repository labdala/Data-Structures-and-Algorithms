package assn03;

public class Main {

    public static void main(String[] args) {
        LinkedList list = new LinkedList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
//        list.add(1);
        list.add(4);
//        list.add(2);

        LinkedList list2 = new LinkedList();
        list2.add(1);
        list2.add(2);
        list2.add(3);

        list.merge(list2);
        for(int i=0;i<list.size();i++)
        {
            System.out.println(list.get(i));
        }

        System.out.println(list.isEqual(list2));
    }
}
