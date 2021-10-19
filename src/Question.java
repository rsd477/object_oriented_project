// Richard Scott
// 10/26/2020
// Question

abstract class Question implements java.io.Serializable{
    protected String prompt;

    public abstract void add();
    public abstract void modify();
    public abstract void display();
    public abstract String take();

}