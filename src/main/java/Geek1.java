// { Driver Code Starts
//Initial Template for Java

import java.io.*;
import java.util.Scanner;

class Node1 {

    int data;

    Node1 next;

    Node1(int d) {
        this.data = d;
        this.next = null;
    }
}

class Geek1 {

    public static Node1 cur2 = null;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            Node1 head = null;
            Node1 cur = null;
            int n = sc.nextInt();
            int p = sc.nextInt();
            cur = null;
            for (int i = 0; i < n; i++) {
                int d = sc.nextInt();
                Node1 ptr = new Node1(d);
                if (head == null) {
                    head = ptr;
                    cur = ptr;
                } else {
                    cur.next = ptr;
                    cur = ptr;
                }
            }
            cur2 = head;
            while (p-- > 0) {
                cur2 = cur2.next;
            }
            GfG g = new GfG();
            g.deleteNode(cur2);
            //cur2 = cur2.next;
            while (head != null) {
                System.out.print(head.data + " ");
                head = head.next;
            }
            System.out.println();
        }
    }
}


// } Driver Code Ends


//User function Template for Java
class GfG {

    void deleteNode(Node1 node) {
        //Add your code here
       // node = node.next;
        //node.next = null;

        Node1 temp = node.next;
        node.data = temp.data;
        node.next = temp.next;
        System.gc();
    }
}