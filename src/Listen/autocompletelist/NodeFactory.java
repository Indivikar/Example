package Listen.autocompletelist;


import javafx.scene.Node;

public interface NodeFactory<DataType>
{
    Node createNode(DataType data);
}

