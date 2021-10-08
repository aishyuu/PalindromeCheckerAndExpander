
public class Stack<T> implements StackInterface<T>{
  LinkedList<T> list = new LinkedList<T>();

  public void push(T data){
    list.add(data, list.size());
  }

  public T pop(){
    T temp = list.get(list.size() - 1);
    list.remove(list.size() - 1);
    return temp;
  }

  public int size(){
    return list.size();
  }

  public T peek(){
    return list.get(list.size()- 1);
  }

  public String toString(){ //prints out stack from top down
    if(list.size() == 0){
      return "[ ]";
    }
    else{
      String build = "";
      for(int i = 0; i < list.size(); i++){
        build = build + list.get(list.size() - i);
      }
      return "[" + build + "]";
    }
  }
}
