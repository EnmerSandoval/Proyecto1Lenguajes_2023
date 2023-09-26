/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import guru.nidi.graphviz.attribute.Label;
import guru.nidi.graphviz.attribute.Shape;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.Factory;
import guru.nidi.graphviz.model.Graph;
import guru.nidi.graphviz.model.Link;
import guru.nidi.graphviz.model.Node;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author Usuario
 */
public class DibujoPalabras {
    public void dibujar(String palabra) {
        String inputString = palabra;
        Graph graph = createAfdGraph(inputString);

        try {
            Graphviz.fromGraph(graph)
                    .render(Format.PNG)
                    .toFile(new File("afd_diagram.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static Graph createAfdGraph(String inputString) {
        Graph g = Factory.graph("AFD").directed();

        Node initial = Factory.node("S").with(Shape.POINT);
        Node accepting = Factory.node("0").with(Shape.DOUBLE_CIRCLE);
        g = g.with(initial.link(accepting));

        Node current = accepting;
        for (int i = 0; i < inputString.length(); i++) {
            char c = inputString.charAt(i);
           Node nextState;
            if (i + 1 == inputString.length()) {

                nextState = Factory.node("q" + (i + 1)).with(Shape.DOUBLE_CIRCLE);
            } else {

                nextState = Factory.node("q" + (i + 1)).with(Shape.CIRCLE);
            }
            Link link = Factory.to(nextState).with(Label.of(String.valueOf(c)));
            g = g.with(current.link(link));
            current = nextState;
        }

        return g;
    }
}
