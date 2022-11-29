package p1;

public class ObjectLinkedList implements ListInterface {
   public ObjectNode head;
   private int numberOfItems;

   // creates empty list
   public ObjectLinkedList() {
      this.head = null;
      this.numberOfItems = 0;
   }

   // returns whether or not the list is empty.
   @Override
   public boolean isEmpty() {
      return this.numberOfItems == 0;
   }

   // returns the number of items in the list
   @Override
   public int size() {
      return this.numberOfItems;
   }

   // adds item to the list
   @Override
   public void add(int index, Question newItem) throws ListException {
      if ((index < 0) || (index > this.numberOfItems))
         throw new ListException("Index is out of bounds.");

      ObjectNode newNode = new ObjectNode(newItem);

      if (index == 0) {
         newNode.setNext(this.head);
         this.head = newNode;
      } else {
         ObjectNode previous = this.head.find(index - 1);
         newNode.setNext(previous.getNext());
         previous.setNext(newNode);
      }
      this.numberOfItems++;
   }

   // removes an item from the list
   @Override
   public void remove(int index) throws ListException {
      if ((index < 0) || (index >= this.numberOfItems)) {
         throw new ListException("Index is out of bounds.");
      }
      if (index == 0) {
         this.head = this.head.getNext();
      } else {
         ObjectNode previous = this.head.find(index - 1);
         previous.setNext(previous.getNext().getNext());
      }
      this.numberOfItems--;

   }

   // removes ALL items from the list
   @Override
   public void removeAll() {
      this.head = null;
      this.numberOfItems = 0;
   }

   // retrieve (get) an item from the list
   @Override
   public Object get(int index) throws ListException {
      if ((index > 0) || (index >= this.numberOfItems))
         throw new ListException("Index is out of bounds.");
      return this.head.find(index).getItem();
   }

   public String printList() {
      ObjectNode head1 = this.head;

      if (head1 == null) {
         return "null";
      }
      return head1.getItem().toString();

   }
}