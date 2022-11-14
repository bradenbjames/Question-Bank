package p1;

public interface ListInterface {
   // Creating an empty list
   // This is differed to the implementation's constructor

   // Determine whether a list is empty
   public boolean isEmpty();

   // Determine the number of items on the list
   public int size();

   // Add an item ata given position on the list
   public void add(int index, Question item) throws ListException;

   // Remove an item at a given positin on the list
   public void remove(int index) throws ListException;

   // Remove all the items from the list
   public void removeAll();

   // Retrieve (get) the item at a given position on the list
   public Object get(int index) throws ListException;
}