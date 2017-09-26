package Streams;

import java.util.stream.IntStream;

import javafx.beans.Observable;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener.Change;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;


public class DynamicFilteredListTest {

    public static void main(String[] args) {

        ObservableList<Item> baseList = FXCollections.observableArrayList(item -> 
                new Observable[] {item.filteredProperty()});

        FilteredList<Item> list = new FilteredList<>(baseList, t -> ! t.isFiltered());

        list.addListener((Change<? extends Item> c) -> {
            while (c.next()) {
                if (c.wasAdded()) {
                    System.out.println(c.getAddedSubList()+ " added to filtered list");
                }
                if (c.wasRemoved()) {
                    System.out.println(c.getRemoved()+ " removed from filtered list");
                }
            }
        });

        System.out.println("Adding ten items to base list:\n");

        IntStream.rangeClosed(1, 10).mapToObj(i -> new Item("Item "+i)).forEach(baseList::add);

        System.out.println("\nFiltered list now:\n"+list);

        System.out.println("\nSetting filtered flag for alternate items in base list:\n");

        IntStream.range(0, 5).map(i -> 2*i).mapToObj(baseList::get).forEach(i -> i.setFiltered(true));

        System.out.println("\nFiltered list now:\n"+list);
    }


    public static class Item {
        private final StringProperty name = new SimpleStringProperty() ;
        private final BooleanProperty filtered = new SimpleBooleanProperty() ;

        public Item(String name) {
            setName(name);
        }

        public final StringProperty nameProperty() {
            return this.name;
        }

        public final String getName() {
            return this.nameProperty().get();
        }

        public final void setName(final String name) {
            this.nameProperty().set(name);
        }

        public final BooleanProperty filteredProperty() {
            return this.filtered;
        }

        public final boolean isFiltered() {
            return this.filteredProperty().get();
        }

        public final void setFiltered(final boolean filtered) {
            this.filteredProperty().set(filtered);
        }

        @Override
        public String toString() {
            return getName();
        }
    }
}