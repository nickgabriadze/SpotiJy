package pgdp;


public class PersonalArray<T> {
    T[] array;

    public PersonalArray(T[] prevArray){
        this.array = prevArray;
    }

    public void append(T el){
        T[] newArray = (T[]) new Object[this.array.length+1];
        System.arraycopy(this.array, 0, newArray, 0, this.array.length);
        newArray[newArray.length-1] = el;

        this.array = newArray;
    }

    public String toString(){
       StringBuilder stringRepresentation = new StringBuilder("[");

       for(int i = 0; i < this.array.length; i++){
           if(i == this.array.length-1){
               stringRepresentation.append(this.array[i]).append("]");
           }else{
               stringRepresentation.append(this.array[i]).append(", ");
           }
       }

       return stringRepresentation.toString();
    }

    public int size(){
        return this.array.length;
    }

    public T get(int i){
        return this.array[i];
    }

    public boolean isItThere(T el){
        for(int i = 0; i < this.array.length; i++){
            if(this.get(i) == el){
                return true;
            }
        }
        return false;
    }
}
