import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

record Place(String name, int distance){

    @Override
    public String toString() {
        return String.format("%s (%d)", name, distance);
    }
}

public class Main {
    public static void main(String[] args) {

        LinkedList<Place> placeList = new LinkedList<>();
//        places.add(new Place("Adelaide", 1374));
//        places.add(new Place("Alice Springs", 2771));
//        places.add(new Place("Brisbane", 917));
//        places.add(new Place("Darwin", 3972));
//        places.add(new Place("Melbourne", 877));
//        places.add(new Place("Perth", 3923));

        // Add places in random order, method will add the place in sorted way
        addPlace(placeList, new Place("Adelaide", 1374));
        addPlace(placeList, new Place("Alice Springs", 2771));
        addPlace(placeList, new Place("Brisbane", 917));
        addPlace(placeList, new Place("Darwin", 3972));
        addPlace(placeList, new Place("Melbourne", 877));
        addPlace(placeList, new Place("Perth", 3923));
        addPlace(placeList, new Place("Sydney", 0));
//        System.out.println(placeList);
//        printMenu();
        Scanner scanner = new Scanner(System.in);
        boolean quitLoop = false;
        ListIterator<Place> iterator = placeList.listIterator();
        boolean forward = true;

        // Print the menu and perform action as selected by user
        printMenu();
        while (!quitLoop) {

            // Check if it's the start or of loop to decide if forward or backward is possible
            if (!iterator.hasPrevious()) {
                System.out.println("Originating : " + iterator.next());
                forward = true;
            }
            if (!iterator.hasNext()) {
                System.out.println("Final : " + iterator.previous());
                forward = false;
            }

            System.out.print("Enter the action: ");
            String action = scanner.nextLine().toUpperCase().substring(0, 1);
            switch (action) {
                case "F":
                    // In case of reverse direction, set the iterator position correctly
                    if (!forward) {
                        forward = true;
                        if (iterator.hasNext()) {
                            iterator.next();
                        }
                    }
                    if (iterator.hasNext()) {
                        System.out.println(iterator.next());
                    }
                    break;
                case "B":
                    if (forward) {
                        forward = false;
                        if (iterator.hasPrevious()) {
                            iterator.previous();
                        }
                    }
                    if (iterator.hasPrevious()) {
                        System.out.println(iterator.previous());
                    }
                    break;
                case "L":
                    System.out.println(placeList);
                    break;
                case "M":
                    printMenu();
                    break;
                case "Q":
                    System.out.println(action);
                    quitLoop = true;
                    break;
                default:
                    System.out.println("Invalid action selected. Please select a valid action to proceed.");
            }
        }
    }

    private static void addPlace(LinkedList<Place> placeList, Place place) {

        // Do not add if the place is duplicate
        for (Place existingPlace : placeList) {
            if (existingPlace.name().equalsIgnoreCase(place.name())) {
                System.out.println("Found Duplicate: " + existingPlace);
                return;
            }
        }

        // Add the place in distance sorting order
        int index = 0;
        for (Place existingPlace : placeList) {

            if (existingPlace.distance() > place.distance()) {
                placeList.add(index, place);
                return;
            }
            index++;
        }

        placeList.add(place);
    }

    private static void printMenu() {

        System.out.println("""
                Available actions (select word or letter:
                (F)orward
                (B)ackward
                (L)ist Places
                (M)enu
                (Q)uit""");
    }
}