package assn03;

import java.util.*;

public class LinkedList<T> {
    private Node<T> head = null;
    private Node<T> tail = null;
    private int size = 0;


    /**
     * Task1
     * Remove the node at index i of the list.
     * Note that the first element is at index 0
     * If i is larger than the size of the list, throw an IndexOutOfBounds Exception
     *
     * ex: list: A -> B -> C -> D
     *     i: 1
     *     list after removeAtIndex: A -> C -> D
     *
     * @param i    - index of node to remove
     */
    public void removeAtIndex(int i) {
        validIndex(i);
        Node previous = head;
        int count=0;
        if(size==1)
        {
            head.setValue(null);
        }
        else if(i==0)
        {
            head=previous.getNext();
        }
        else if (i==size-1)
        {
            while(count!=size-1)
            {
                previous = previous.getNext();
                count++;
            }
            previous.setNext(null);
            tail=previous.getNext();
        }
        else
        {
            while(count!=i-1)
            {
                previous = previous.getNext();
                count++;
            }
            previous.setNext(previous.getNext().getNext());
        }
        size--;
    }



    /**
     * Task2
     * Return true if this linked list is equal to the list argument, false otherwise.
     * Two lists are equal if they have the same size, and the same
     * elements in the same order.
     * ex:  list: 1 -> 4 -> 2
     *      list2: 1 -> 4 -> 2
     *      return: true
     *
     *      list: 1 -> 5
     *      list2: 2 -> 5
     *      return false;
     *
     * @param list2 - the list to merge into the current list
     * @return true if the lists have the same elements in the same order, false otherwise
     */
    public boolean isEqual(LinkedList list2) {
        Node previous1 = this.head;
        Node previous2 = list2.getHead();
        if(size==list2.size()) {
            if (size == 0) return true;
            else
                for (int i = 0; i < size; i++) {
                    if (previous2.getValue() != previous1.getValue())
                        return false;
                    else
                        previous2 = previous2.getNext();
                    previous1 = previous1.getNext();
                }
            return true;
        }
        else
            return false;
    }



    /**
     * Task3
     * Given a sorted linked list, remove the duplicate values from the list
     * ex: list: 5 -> 6 -> 7 -> 7 -> 7 -> 8 -> 8 -> 9
     *     list after removeRepeats: 5 -> 6 -> 7 -> 8 -> 9
     *
     */
    public void removeRepeats() {
        Set<Object> unrepeatedValues = new HashSet<>();
        Node previous = head;
        if (size == 0 || size == 1)
            return;
        else {
            int i = 1;
            unrepeatedValues.add(head.getValue());
            Node current = previous.getNext();
            while (current!=null) {
//                System.out.println("-----------------");
//                System.out.println("previous=" + previous.getValue());
//                System.out.println("current=" + current.getValue());
                if (!unrepeatedValues.contains(current.getValue())) {
//                    System.out.println("not repeated");
                    unrepeatedValues.add(current.getValue());
                } else {
                    removeAtIndex(i);
//                    System.out.println("i= "+i+" is repeated and removed; size=" + size);
                    current=previous.getNext();
                }

                previous = current;
                current = current.getNext();
//                System.out.println("previous=" + previous.getValue());
//                if(current!=null) System.out.println("current=" + current.getValue());
                i++;
            }

        }
    }



    /**
     * Task4
     * Reverse the list
     *
     * ex list:  10 -> 9 -> 8 -> 7
     *    list after reverse: 7 -> 8 -> 9 -> 10
     *
     */
    public void reverse() {
        if(size==0 || size==1) return;
        else
        {
            Node previous = head;
            Node current = previous.getNext();
            int nextIdxNeedsUpdate=size;
            for(int i=size-1; i>=0;i--)
            {
                previous = head;
                current = previous.getNext();
                System.out.println("--------------- i = "+i);
                for(int j=0;j<i-1;j++)
                {
                    previous = current;
                    current = current.getNext();
                    nextIdxNeedsUpdate++;
                }
                System.out.println("previous="+previous.getValue());
                System.out.println("current="+current.getValue());
                current.setNext(previous);
            }
            head.setNext(null);
            head = tail;
            Node tmp = head;
            while(tmp.hasNext()) tmp = tmp.getNext();
            tail=tmp;
        }
    }





    /**
     * Task5
     * Merge the given linked list into the current list. The 2 lists will always be
     * either the same size, or the current list will be longer than list2.
     * The examples below show how to handle each case.
     *
     * Note: Do NOT create and return a new list, merge the second list into the first one.
     *
     * ex: list: 1 -> 2 -> 3
     *     list2: 4 -> 5 -> 6
     *     return: 1 -> 4 -> 2 -> 5 -> 3 -> 6
     *
     *     list: 1 -> 2 -> 3 -> 4
     *     list2: 5 -> 6
     *     return 1 -> 5 -> 2 -> 6 -> 3 -> 4
     *
     * @param list2
     */
    public void merge(LinkedList list2) {
        System.out.println("Merging");
        Node previous = head;
        Node previous2 = list2.getHead();
        Node current = previous.getNext();
        Node current2 = previous2.getNext();
        for(int i=list2.size-1; i>=0;i--)
        {
            previous = head;
            previous2 = list2.getHead();
            current = previous.getNext();
            current2 = list2.getHead().getNext();
            while(current2.hasNext())
            {
                previous = current;
                previous2 = current2;
                current = previous.getNext();
                current2 = current2.getNext();
            }
            System.out.println("current="+current.getValue());
            System.out.println("current2="+current2.getValue());
            current2.setNext(current.getNext());
            current.setNext(current2);
            previous2.setNext(null);
            size++;
        }

        return;
    }


    /* Implementation given to you. Do not modify below this. */

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    public boolean contains(Object element) {
        Node<T> current = head;
        while(current != null) {
            if(current.getValue().equals(element)) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    public T[] toArray() {
        T[] arr =  (T[]) new Object[size()];
        Node<T> current = head;
        int i = 0;
        if(isEmpty()) {
            return arr;
        }
        while(current != null){
            arr[i] = current.getValue();
            current = current.getNext();
            i++;
        }
        return arr;
    }

    public void add(Object element) {
        Node<T> newNode = new NodeImpl<T>((T) element, null);
        if(isEmpty()) {
            head = newNode;
            tail = newNode;
            size++;
        } else {
            tail.setNext(newNode);
            tail = newNode;
            size++;
        }

    }

    public boolean remove(Object element) {
        Node<T> current = head;
        if(isEmpty()) {
            return false;
        }
        if(current.getValue() == element){
            head = head.getNext();
            size--;
            return true;
        }
        while(current.getNext().getValue() != element) {
            current = current.getNext();
            if(current == null) {
                return false;
            }
        }
        if(current.getNext().getNext() == null) {
            tail = current;
        }
        current.setNext(current.getNext().getNext());
        size--;
        return true;
    }

    public T get(int index) {
        validIndex(index);
        Node<T> current = head;
        int i = 0;
        while (i < index) {
            current = current.getNext();
            i++;
        }
        return current.getValue();
    }

    public T set(int index, Object element) {
        validIndex(index);
        Node<T> current = head;
        T prevValue = null;
        int i = 0;
        if(index == 0) {
            prevValue = head.getValue();
            head.setValue((T) element);
        } else {
            while(current != null) {
                if(i == index) {
                    prevValue = current.getValue();
                    current.setValue((T) element);
                    return prevValue;
                }
                current = current.getNext();
                i++;
            }
        }

        return prevValue;
    }

    public void add(int index, Object element) {
        if(index > size) {
            validIndex(index);
        }
        Node<T> current = head;
        int i = 0;
        if(index == 0) {
            if(isEmpty()) {
                add(element);
                return;
            } else {
                Node<T> newNode = new NodeImpl<T>((T) element, head.getNext());
                head = newNode;
                size++;
                return;
            }

        }  else if(index == size) {
            add(element);
            return;
        }
        while(current != null) {
            if(i == (index - 1)) {
                Node<T> temp = current.getNext();
                Node<T> newNode = new NodeImpl<T>((T) element, temp);
                current.setNext(newNode);
                size++;
                return;
            } else {
                current = current.getNext();
                i++;
            }
        }
    }

    public int indexOf(Object element) {
        Node<T> current = head;
        int index = 0;
        while(current != null) {
            if(current.getValue().equals((T) element)) {
                return index;
            }
            index++;
            current = current.getNext();
        }
        return -1;
    }

    public int lastIndexOf(Object element) {
        Node<T> current = head;
        int index = -1;
        int i = 0;
        while(current != null) {
            if(current.getValue().equals ((T) element)) {
                index = i;
            }
            i++;
            current = current.getNext();
        }
        return index;
    }

    public void validIndex(int i) {
        if(i < 0 || i >= size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
    }
    public Node<T> getHead() {
        return head;
    }

    @Override
    public String toString() {
        String list = "";
        Node<T> current = head;
        while(current != null) {
            if(current.getNext() == null)
                list+= current.getValue();
            else
                list += current.getValue() + " -> ";
            current = current.getNext();
        }
        return list;
    }
}