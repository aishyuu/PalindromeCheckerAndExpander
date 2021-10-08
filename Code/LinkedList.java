public class LinkedList<T> implements ListInterface<T> {
  public static class Node<T>{
    T data;
    Node<T> rest;

    public Node(T d, Node<T> r){
      this.data = d;
      this.rest = r;
    }
  }
    protected Node<T> head = null;
    protected Node<T> tail = null;
    protected int size = 0;

    public T get(int index){
      if(index < 0 || index >= this.size){
        //if you don't return here, will simply
        //move forward to the next if statement
        //and check if the condition is met
        return null;
      }
      if(index == 0){
        return this.head.data;
      }
      else if(index == this.size - 1){
        return this.tail.data;
      }
      else{
        Node<T> current = this.head;
        for(int i = 0; i < index; i++){
          current = current.rest;
        }
        return current.data;
      }
    }

    public void add(T data, int index){
      if(this.size == 0)
        this.head = this.tail = new Node<T>(data, null);
      else if (index == 0)
        this.head = new Node<T>(data, this.head);
      else if(index == this.size){
        this.tail.rest = new Node<T>(data, null);
        this.tail = tail.rest;
      }
      else if(index > 0 && index < this.size){
        Node<T> current = this.head;
        for(int i = 0; i < index - 1; i++){
          current = current.rest;
        }
        //current is the index right before (aka index - 1)
        //Check if things fuck up
        current.rest = new Node<T>(data, current.rest);

      }
      this.size++;
    }

    public void remove(int index){
      if(index < 0 || index >= this.size) 
        return;
      
      if (size == 0){
        System.out.println("Size is 0. Cannot remove anything.");
        return;
      }

      if (size == 1)
      {
        this.head = this.tail = null;
        this.size--;
      }
      else if(index == 0){
        this.head = this.head.rest;
        this.size--;
      }
      else if(index > 0 && index < this.size-1){
        Node<T> current = this.head;
        for(int i = 0; i < index-1; i++){
          current = current.rest;
        }
        current.rest = current.rest.rest;
        this.size--;
      }
      else{ //This last one is specifically removing the tail :)
        Node<T> current = this.head;
        for(int i = 0; i < index-1; i++){
          current = current.rest;
        }
        current.rest = current.rest.rest;
        this.tail = current;
        this.size--;
      }
    }

    public int size(){
      return this.size;
    }
  }
