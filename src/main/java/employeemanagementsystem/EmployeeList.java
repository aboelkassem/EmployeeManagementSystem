/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employeemanagementsystem;

import java.util.ArrayList;

/**
 *
 * @author Mohamed
 */
public class EmployeeList {
    
    private class Node{
        public Employee data;
        public Node next;
        public Node prev;

        public Node(Employee emp)
        {
            this.data = emp;
            this.next = null;
            this.prev = null;
        }
    }
    Node head = null;
    
    public void append(Employee emp){
        Node newNode = new Node(emp);

        if (head == null)
        {
            head = newNode;
        }
        else
        {
            Node temp = head;
            while (temp.next != null)
            {
                temp = temp.next;
            }
            temp.next = newNode;
            newNode.prev = temp;
        }
    }
    
    public void insert_at_pos(Employee emp, int pos){
        Node newNode = new Node(emp);
        // empty LinkedList
        if (head == null)
        {
            head = newNode;
            return;
        }
        if (pos == 0 || pos < 0)
        {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
            return;
        }

        Node temp = head;

        for (int i = 0; i < pos && temp.next != null; i++)   // to get the node before target node , second condition to make sure it's still at range of linkedlist
        {
            temp = temp.next;
        }
        // special case if user entered position bigger than the range of linked list
        if (temp == null)
        {
            this.append(emp);   // append it to last of list,don't care about position
            return;
        }

        newNode.prev = temp.prev;
        temp.prev.next = newNode;
        temp.prev = newNode;
        newNode.next = temp;
    }
    
    public void display() {
        Node temp = head;
        while (temp != null)
        {
            System.out.println("Employee Number = " + temp.data.getEmp_No());
            System.out.println("Employee Name = " + temp.data.getName());
            System.out.println("Employee Department = " + temp.data.getDepartment());
            System.out.println("DateOfBirth = " + temp.data.getDateOfBirth());
            System.out.println("Employee Salary = " + temp.data.getSalary());
            System.out.println("===================================================");
            temp = temp.next;
        }
    }
    
    public ArrayList<Employee> traverseInArrayList() {
        Node temp = head;
        ArrayList<Employee> list = new ArrayList<>();
        while (temp != null)
        {
            list.add(temp.data);
            temp = temp.next;
        }
        return list;
    }
    
    public Employee linearSearch(int value) throws Exception {  
        int i = 1;  
        boolean flag = false;  
        Node current = head;  
          
        //Checks whether the list is empty  
        if(head == null) {  
            throw new Exception("List is empty");
        }  
        while(current != null) {  
            //Compare value to be searched with each node in the list  
            if(current.data.getEmp_No() == value) {  
                flag = true;  
                break;  
            }  
            current = current.next;  
            i++;  
        }  
        if(flag){
            System.out.println("Employee Found in the list at the position : " + i);  
            System.out.println("==================================================");  
            return current.data;
        }else{  
             return null;
        }
    }  
    
    public Employee binarySearch(int value) { 
        Node start = head; 
        Node last = null;
  
        do
        { 
            // Find Middle 
            Node mid = middleNode(start, last); 
  
            // If middle is empty 
            if (mid == null) 
                return null; 
  
            
            if (mid.data.getEmp_No() == value){  // If value is present at middle 
                return mid.data; 
            }
            else if (mid.data.getEmp_No() < value){   // If value is more than mid 
                start = mid.next; 
            } 
            else if (mid.data.getEmp_No() > value) { // If the value is less than mid. 
                last = mid; 
            }
        } while (last == null || last != start); 
  
        // value not found
        return null; 
    } 
    
    public void selectionSort() {
        Node current = null, index = null;  
        int temp;  
        //Check whether list is empty  
        if(head == null) {  
            return;  
        }  
        else {  
            //Current will point to head  
            for(current = head; current.next != null; current = current.next) {  
                //Index will point to node next to current  
                for(index = current.next; index != null; index = index.next) {  
                    //If current's data is greater than index's data, swap the data of current and index  
                    if(current.data.getEmp_No() > index.data.getEmp_No()) {  
                        temp = current.data.getEmp_No();  
                        current.data.setEmp_No(index.data.getEmp_No());  
                        index.data.setEmp_No(temp);
                    }  
                }  
            }  
        }
    }
    
    public void insertionSort() {  
        if (head == null || head.next == null)
            return;
 
        Node newHead = new Node(head.data);
        Node pointer = head.next;

        // loop through each element in the list
        while (pointer != null) {
            // insert this element to the new list

            Node innerPointer = newHead;
            Node next = pointer.next;

            if (pointer.data.getEmp_No() <= newHead.data.getEmp_No()) {
                Node oldHead = newHead;
                newHead = pointer;
                newHead.next = oldHead;
            } else {
                while (innerPointer.next != null) {

                    if (pointer.data.getEmp_No() > innerPointer.data.getEmp_No() && pointer.data.getEmp_No() <= innerPointer.next.data.getEmp_No()) {
                        Node oldNext = innerPointer.next;
                        innerPointer.next = pointer;
                        pointer.next = oldNext;
                    }
                    innerPointer = innerPointer.next;
                }

                if (innerPointer.next == null && pointer.data.getEmp_No() > innerPointer.data.getEmp_No()) {
                    innerPointer.next = pointer;
                    pointer.next = null;
                }
            }

            // finally
            pointer = next;
        }
        head = newHead;
    }
    
    public Node mergeSort(Node head) {
        // base case: 0 or 1 node
        if (head == null || head.next == null) {
            return head;
        }

        // split head into 'a' and 'b' sublists
        Node a = head, b;

        Node slow = split(head);
        b = slow.next;
        slow.next = null;

        // recursively sort the sub-lists
        a = mergeSort(a);
        b = mergeSort(b);

        // merge the two sorted lists together
        head = merge(a, b);
        return head;
    }
    
    public void remove(Employee emp){
        if (head == null)
        {
            return;
        }
        Node temp = head;

        if (head.data == emp)
        {
            head = head.next;
            if (head != null)   // if the linked list have only one Node Skip this condition
            {
                head.prev = null;
            }
            return;
        }

        while (temp != null && temp.data != emp)
        {
            temp = temp.next;
        }

        if (temp == null) // finished searched and don't found target Node
        {
        }
        else
        {
            temp.prev.next = temp.next;
            if (temp.next != null) // if the deleted element at the last of list Skip this condition
            {
                temp.next.prev = temp.prev; 
            }
        }
    }
    
    public void removeWithEmployeeNumber(int emp_num){
        if (head == null)
        {
            return;
        }
        Node temp = head;

        if (head.data.getEmp_No() == emp_num)
        {
            head = head.next;
            if (head != null)   // if the linked list have only one Node Skip this condition
            {
                head.prev = null;
            }
            return;
        }

        while (temp != null && temp.data.getEmp_No() != emp_num)
        {
            temp = temp.next;
        }

        if (temp == null) // finished searched and don't found target Node
        {
        }
        else
        {
            temp.prev.next = temp.next;
            if (temp.next != null) // if the deleted element at the last of list Skip this condition
            {
                temp.next.prev = temp.prev; 
            }
        }
    }
    
    public void remove_at_pos(int pos) throws Exception{
        if (head == null)
        {
            return;
        }

        if (pos == 0)
        {
            head = head.next;
            if (head != null)   // if the linked list have only one Node Skip this condition
            {
                head.prev = null;
            }
        }
        else
        {
            Node temp = head;
            for (int i = 0; i < pos && temp.next != null; i++)   // to get the node before target node , second condition to make sure it's still at range of linkedlist
            {
                temp = temp.next;
            }
            if (temp == null) // finished searched and don't found target Node
            {
                throw new Exception("The Position Not Found, please try to remove existing index");
            }
            else
            {
                temp.prev.next = temp.next;
                if (temp.next != null) // if the deleted element at the last of list Skip this condition
                {
                    temp.next.prev = temp.prev;
                }
            }
        }
    }
    
    public int size() {
        if (head == null)
            return 0;
        Node n = head;
        int size = 0;
        while (n != null) {
            size++;
            n = n.next;
        }
        return size;
    }
    
    
    // =====================> Methods Helpers <====================
    
    // Function to find middle element 
    // using Fast and Slow pointers 
    public static Node middleNode(Node start, Node last) { 
        if (start == null) 
            return null; 
  
        Node slow = start; 
        Node fast = start.next; 
  
        while (fast != last) 
        { 
            fast = fast.next; 
            if (fast != last)  
            { 
                slow = slow.next; 
                fast = fast.next; 
            } 
        } 
        return slow; 
    } 
    
    // Function to split nodes of the given doubly linked list into
    // two halves using the fast/slow pointer strategy
    public static Node split(Node head)
    {
        Node slow = head;
        Node fast = head.next;

        // Advance 'fast' by two nodes, and advance 'slow' by single node
        while (fast != null)
        {
            fast = fast.next;
            if (fast != null)
            {
                slow = slow.next;
                fast = fast.next;
            }
        }

        return slow;
    }
  
    // Recursive function to merge nodes of two sorted lists together
    // into a single sorted list
    public static Node merge(Node a, Node b)
    {
        // Base cases
        if (a == null) {
            return b;
        }

        if (b == null) {
            return a;
        }

        // Pick either a or b, and recur
        if (a.data.getEmp_No() <= b.data.getEmp_No())
        {
            a.next = merge(a.next, b);
            a.next.prev = a;
            a.prev = null;
            return a;
        }
        else
        {
            b.next = merge(a, b.next);
            b.next.prev = b;
            b.prev = null;
            return b;
        }
    }
}
