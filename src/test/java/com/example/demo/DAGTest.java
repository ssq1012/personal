package com.example.demo;

import com.example.demo.pojo.Graph;
import com.example.demo.pojo.Node;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ssq
 * @create on 2022/09/19
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DAGTest {
    static Graph g = new Graph();

    /*    static {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        node1.addNeighbor(2);
        node2.addNeighbor(4);
        node3.getNeighbors().add(4);
        node3.getNeighbors().add(5);
        node3.getNeighbors().add(6);
        g.addNode(node1);
        g.addNode(node2);
        g.addNode(node3);
        g.addNode(node4);
        g.addNode(node5);
        g.addNode(node6);
    }*/

    static {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        node1.getNeighbors().add(2);
        node3.getNeighbors().add(6);
        node4.getNeighbors().add(3);
//        node4.getNeighbors().add(6);
        node6.getNeighbors().add(4);
        node6.getNeighbors().add(1);
        node6.getNeighbors().add(5);
        g.addNode(node1);
        g.addNode(node2);
        g.addNode(node3);
        g.addNode(node4);
        g.addNode(node5);
        g.addNode(node6);
    }

    @Test
    public void contextLoads() {

    }
    @Test
    public void context() {
        int count = 0;
        int x = 250;
        while (x != 0){
            x = x - x&(x-1);
            count++;
        }
        System.out.println(count);
    }

    public void swap(String a,String b){
        String tmp =a ;
        a = b;
        b =tmp;
    }

    @Test
    public void DagTest(){
        // List where we'll be storing the topological order
        List<Integer> order = new ArrayList<>();

       //0:未被遍历未被输出  1：被遍历未输出 2：被遍历被输出
        Map<Integer, Integer> visited = new HashMap<>();
        for (Node tmp: g.getNodes())
            visited.put(tmp.getId(), 0);

        // We go through the nodes using black magic
        for (Node tmp: g.getNodes()) {
            if (2 != visited.get(tmp.getId()))
                blackMagic(g, tmp.getId(), visited, order);
        }

        // We reverse the order we constructed to get the
        // proper toposorting
        Collections.reverse(order);
        System.out.println(order);
    }

    private void blackMagic(Graph g, int v, Map<Integer, Integer> visited, List<Integer> order) {
        // Mark the current node as visited
        Integer integer = visited.get(v);
        if (1 == integer){
            //重复访问
            System.out.println("有环：" + v);
            throw new RuntimeException();
        }
        visited.replace(v, 1);
        // We reuse the algorithm on all adjacent nodes to the current node
        for (Integer neighborId: g.getNode(v).getNeighbors()) {
            if (2 != visited.get(neighborId))
                blackMagic(g, neighborId, visited, order);
        }
        // Put the current node in the array
        visited.replace(v, 2);
        order.add(v);
    }

}
