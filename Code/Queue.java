
public class Queue<T> {
  LinkedList<T> list = new LinkedList<T>();

  public void enqueue(T data){
    list.add(data, list.size());
  }

  public T dequeue(){
    T temp = list.get(0);
    list.remove(0);
    return temp;
  }

  public int size(){
    return list.size();
  }

  public T peek(){
    return list.get(0);
  }

  public String toString(){ //prints out queue from front to back
    if(list.size() == 0){
      return "[ ]";
    }
    else{
      String build = "";
      for(int i = 0; i < list.size(); i++){
        build = build + list.get(i);
      }
      return "[" + build + "]";
    }
  }
}
