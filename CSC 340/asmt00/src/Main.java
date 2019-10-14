import java.util.Scanner;
import java.util.Vector;


public class Main {
    public enum BOOK {
        BOOK("Book [verb] :To arrange something on a particular date.\n" +
                "Book [noun] : A set of pages.\n" + "\t | \n"),
        BOOKVERB("Book [verb] : To arrange something on a particular date.\n" + "\t | \n"),
        BOOKNOUN("Book [noun] : A set of pages.\n" + "\t | \n"),
        BOOKABLE("Bookable [adjective] : Can be ordered.\n" + "\t | \n"),
        BOOKABLEADJECTIVE("Bookable [adjective] : Can be ordered.\n" + "\t | \n"),
        BOOKBINDER("Bookbinder [noun] : A person who fastens the pages of books.\n" + "\t | \n"),
        BOOKBINDERNOWN("Bookbinder [noun] : A person who fastens the pages of books.\n" + "\t | \n"),
        BOOKCASE("Bookcase [noun] : A piece of furniture with shelves.\n" + "\t | \n"),
        BOOKCASENOUN("Bookcase [noun] : A piece of furniture with shelves.\n" + "\t | \n"),
        CSC210("CSC210 [verb] : To learn Java.\n" +
                "CSC210 [noun] : Intro to Java.\n" +
                "CSC210 [adjective] : Comfortable with Objects and Classes., Ready for CSC 220.\n" +
                "\t | \n"),
        CSC210VERB("CSC210 [verb] : To learn Java.\n" + "\t | \n"),
        CSC210NOUN("CSC210 [noun] : Intro to Java.\n" + "\t | \n"),
        CSC210ADJECTIVE("CSC210 [adjective] : Comfortable with Objects and Classes., Ready for CSC 220.\n" + "\t | \n"),
        CSC220("CSC220 [verb] : To create data structures.\n" +
                "CSC220 [noun] : Data Structures.\n" +
                "CSC220 [adjective] : Ready to create complex data structures.\n" +
                "\t | \n"),
        CSC220VERB("CSC220 [noun] : Data Structures.\n" + "\t | \n"),
        CSC220NOUN("CSC220 [verb] : To create data structures.\n" + "\t | \n"),
        CSC220ADJECTIVE("CSC220 [adjective] : Ready to create complex data structures.\n" + "\t | \n"),
        CSC340("CSC340 [noun] : A CS upper division course., Many hours outside of class.\n" +
                "CSC340 [adjective] : = C++ version of CSC210 + CSC220 + more\n" +
                "\t | "),
        CSC340NOUN("CSC340 [adjective] : = C++ version of CSC210 + CSC220 + more\n" + "\t | \n"),
        CSC340ADJECTIVE("CSC340 [noun] : A CS upper division course., Many hours outside of class.\n" + "\t | \n");

        private String description;

            BOOK(String description){
                this.description = description;
            }

        @Override
        public String toString(){
            return this.description;
        }
    }

    public static void main(String[] args) {
        Vector vec = new Vector();
        for(BOOK entry : BOOK.values()){
            vec.add(entry);
        }
        System.out.println("-----DICTIONARY 340 PRO-----\n");
        System.out.println("Search: \n" + "\t |");
        String halt = "!q";
        int found = 0;
        Scanner scan = new Scanner(System.in);
        String choice = scan.nextLine();
        while(!choice.equals(halt)){
            int i = 0;
            choice = choice.toUpperCase();
            choice = choice.replaceAll("\\s+","");
            for(BOOK entry : BOOK.values()){
                if(entry.name().equals(choice)){
                    found = 1;
                    System.out.println(vec.get(i));
                }
             i++;
            }
            if(found == 0){
                System.out.println("<not found>\n" + "\t | \n");
            }
            System.out.println("Search: \n" + "\t |");

            String newChoice = scan.nextLine();
            choice = newChoice;
            found = 0;
        }
    }
}
