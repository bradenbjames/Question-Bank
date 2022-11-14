package p1;

public class ObjectNode {
   private Question item;
   private ObjectNode next;

   public ObjectNode(Question newItem) {
      this.item = newItem;
      this.next = null;
   }

   public Question getItem() {
      return this.item;
   }

   public ObjectNode getNext() {
      return this.next;
   }

   public void setNext(ObjectNode objectNode) {
      this.next = objectNode;
   }

   public ObjectNode find(int position) {
      // base case
      if (position == 0)
         return this;
      else
         return this.next.find(position - 1);
   }

   @Override
   public String toString() {
      if (this.next == null)
         return "(" + this.item + ", null)";
      else
         return "(" + this.item + ", " + this.next.toString() + ")";
   }

}
