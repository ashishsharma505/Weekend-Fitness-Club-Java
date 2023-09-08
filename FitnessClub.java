import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class FitnessClub {
    public static double YOGA_PRICE = 0;
    public static double ZUMBA_PRICE = 0;
    public static double AQUASICE_PRICE = 0;
    public static double BODYSCULPT_PRICE = 0;
    
    private Map<String, List<Booking>> bookings; // Map of names to their bookings

    public FitnessClub() {
        
        bookings = new HashMap<>();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Welcome to the Weekend Fitness Club! What would you like to do?");
            System.out.println("1. View timetable");
            System.out.println("2. Make booking");
            System.out.println("3. Cancel booking");
            System.out.println("4. View bookings");
            System.out.println("5. Attend lesson");
            System.out.println("6. Quit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    System.out.println("Would you like to view the timetable by day or by lesson?");
                    String viewChoice = scanner.nextLine();
                    if (viewChoice.equalsIgnoreCase("day")) {
                        viewTimetableByDay();
                    } else if (viewChoice.equalsIgnoreCase("lesson")) {
                        viewTimetableByLesson();
                    } else {
                        System.out.println("Invalid choice.");
                    }
                    break;
                case 2:
                    makeBooking(scanner);
                    break;
                case 3:
                    cancelBooking(scanner);
                    break;
                case 4:
                    viewBookings(scanner);
                    break;
                case 5:
                    attendLesson(scanner);
                    break;
                case 6:
                    System.out.println("Thank you for using the Weekend Fitness Club system. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        }
    }

    private void viewTimetableByDay() {
        System.out.println("Saturday:");
        System.out.println("  Zumba: 10am-11am");
        System.out.println("  Yoga: 11am-12pm");
        System.out.println("  Aquasice: 12pm-1pm");
        System.out.println("  Bodysculpt: 1pm-2pm");
        System.out.println("Sunday:");
        System.out.println("  Yoga: 10am-11am");
        System.out.println("  Zumba: 11am-12pm");
        System.out.println("  Bodysculpt: 12pm-1pm");
        System.out.println("  Aquasice: 1pm-2pm");
    }

    private void viewTimetableByLesson() {
        System.out.println("Zumba:");
        System.out.println("  Saturday, 10am-11am");
        System.out.println("  Sunday, 11am-12pm");
        System.out.println("Yoga:");
        System.out.println("  Saturday, 11am-12pm");
        System.out.println("  Sunday, 10am-11am");
        System.out.println("Aquasice:");
        System.out.println("  Saturday, 12pm-1pm");
        System.out.println("  Sunday, 1pm-2pm");
        System.out.println("Bodysculpt:");
        System.out.println("  Saturday, 1pm-2pm");
        System.out.println("  Sunday, 12pm-1pm");
    }

    private void makeBooking(Scanner scanner) {
        System.out.println("What is your name?");
        String name = scanner.nextLine();

        System.out.println("Which lesson would you like to book?");
        String lesson = scanner.nextLine();
    
        if (!isValidLesson(lesson)) {
            System.out.println("Invalid lesson.");
            return;
        }
    
        List<Booking> userBookings = bookings.getOrDefault(name, new ArrayList<>());
    
        if (isLessonBooked(userBookings, lesson)) {
            System.out.println("You have already booked this lesson.");
            return;
        }
    
        Booking booking = new Booking(name, lesson);
        userBookings.add(booking);
        bookings.put(name, userBookings);
    
        System.out.println("Booking successful.");
        if (lesson.equals("Yoga")){
            YOGA_PRICE = YOGA_PRICE + 10;
            
        }
        else if (lesson.equals("Zumba")){
            ZUMBA_PRICE = ZUMBA_PRICE + 20;
        }
        else if (lesson.equals("Aquasice")){
            AQUASICE_PRICE = AQUASICE_PRICE + 50;
        }
        else if (lesson.equals("Bodysculpt")){
            BODYSCULPT_PRICE = BODYSCULPT_PRICE + 15;
        }
    }


    private static void generateChampionReport(){
        
        if (YOGA_PRICE < ZUMBA_PRICE) {
            if(AQUASICE_PRICE < ZUMBA_PRICE){
                if(BODYSCULPT_PRICE < ZUMBA_PRICE){
                    System.out.println("The highest income generating lesson is ZUMBA.");
                }
            }
            
        } 

        if (ZUMBA_PRICE < YOGA_PRICE) {
            if(AQUASICE_PRICE < YOGA_PRICE){
                if(BODYSCULPT_PRICE < YOGA_PRICE){
                    System.out.println("The highest income generating lesson is YOGA.");
                }
            }
        } 

        if (ZUMBA_PRICE < AQUASICE_PRICE) {
            if(YOGA_PRICE < AQUASICE_PRICE){
                if(BODYSCULPT_PRICE < AQUASICE_PRICE){
                    System.out.println("The highest income generating lesson is AQUASICE.");
                }
            }
        } 
        if (ZUMBA_PRICE < BODYSCULPT_PRICE) {
            if(AQUASICE_PRICE < BODYSCULPT_PRICE){
                if(YOGA_PRICE < BODYSCULPT_PRICE){
                    System.out.println("The highest income generating lesson is BODYSCULPT.");
                }
            }
        } 

        
        }

    
    private void cancelBooking(Scanner scanner) {
        System.out.println("What is your name?");
        String name = scanner.nextLine();
    
        List<Booking> userBookings = bookings.getOrDefault(name, new ArrayList<>());
    
        if (userBookings.isEmpty()) {
            System.out.println("You have no bookings to cancel.");
            return;
        }
    
        System.out.println("Which lesson would you like to cancel?");
        String lesson = scanner.nextLine();
    
        if (!isLessonBooked(userBookings, lesson)) {
            System.out.println("You have not booked this lesson.");
            return;
        }
    
        userBookings.removeIf(booking -> booking.getLesson().equalsIgnoreCase(lesson));
        bookings.put(name, userBookings);
    
        System.out.println("Booking cancelled.");
    }
    
    private void viewBookings(Scanner scanner) {
        System.out.println("What is your name?");
        String name = scanner.nextLine();
    
        List<Booking> userBookings = bookings.getOrDefault(name, new ArrayList<>());
    
        if (userBookings.isEmpty()) {
            System.out.println("You have no bookings.");
        } else {
            System.out.println("Your bookings:");
            for (Booking booking : userBookings) {
                System.out.println(booking.getLesson());
            }
        }
    }
    private void viewAverageRatingAndNumCustomers() {
        Map<String, List<Double>> ratings = new HashMap<>();
        for (List<Booking> userBookings : bookings.values()) {
            for (Booking booking : userBookings) {
                String lesson = booking.getLesson();
                double rating = booking.getRating();
                List<Double> lessonRatings = ratings.getOrDefault(lesson, new ArrayList<>());
                lessonRatings.add(rating);
                ratings.put(lesson, lessonRatings);
            }
        }
        System.out.println("Lesson\tAverage Rating\tNumber of Customers");
        for (String lesson : ratings.keySet()) {
            List<Double> lessonRatings = ratings.get(lesson);
            double sum = 0.0;
            for (double rating : lessonRatings) {
                sum += rating;
            }
            double averageRating = sum / lessonRatings.size();
            int numCustomers = lessonRatings.size();
            System.out.printf("%s\t%.2f\t\t%d\n", lesson, averageRating, numCustomers);
        }
    }
    
    
    private void attendLesson(Scanner scanner) {
        System.out.println("What is your name?");
        String name = scanner.nextLine();
    
        List<Booking> userBookings = bookings.getOrDefault(name, new ArrayList<>());
    
        if (userBookings.isEmpty()) {
            System.out.println("You have no bookings.");
            return;
        }
    
        System.out.println("Which lesson did you attend?");
        String lesson = scanner.nextLine();
    
        if (!isLessonBooked(userBookings, lesson)) {
            System.out.println("You have not booked this lesson.");
            return;
        }
    
        System.out.println("Did you attend the lesson? (y/n)");
        String attendance = scanner.nextLine();
        boolean attended = attendance.equalsIgnoreCase("y");
    
        System.out.println("Please rate the lesson from 1 to 5:");
        int rating = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
    
        Booking booking = getBooking(userBookings, lesson);
        booking.setAttended(attended);
        booking.setRating(rating);
    
        System.out.println("Attendance and rating recorded.");
    }
    
    private boolean isValidLesson(String lesson) {
        return lesson.equalsIgnoreCase("Zumba") || lesson.equalsIgnoreCase("Yoga") ||
                lesson.equalsIgnoreCase("Aquasice") || lesson.equalsIgnoreCase("Bodysculpt");
    }
    
    private boolean isLessonBooked(List<Booking> bookings, String lesson) {
        for (Booking booking : bookings) {
            if (booking.getLesson().equalsIgnoreCase(lesson)) {
                return true;
            }
        }
        return false;
    }
    
    private Booking getBooking(List<Booking> bookings, String lesson) {
        for (Booking booking : bookings) {
            if (booking.getLesson().equalsIgnoreCase(lesson)) {
                return booking;
            }
        }
        return null;
    }
    
    public static void main(String[] args) {
        FitnessClub system = new FitnessClub();
        Scanner scanner = new Scanner(System.in);
    
        while (true) {
            System.out.println("Welcome to the Weekend Fitness Club System.");
            System.out.println("1. View timetable by day");
            System.out.println("2. View timetable by lesson");
            System.out.println("3. Make booking");
            System.out.println("4. Cancel booking");
            System.out.println("5. View bookings");
            System.out.println("6. Attend lesson");
            System.out.println("7. View average rating and number of customers for each lesson");

            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
    
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character
    
            switch (choice) {
                case 1:
                    system.viewTimetableByDay();
                    break;
                case 2:
                    system.viewTimetableByLesson();
                    break;
                case 3:
                    system.makeBooking(scanner);
                    break;
                case 4:
                    system.cancelBooking(scanner);
                    break;
                case 5:
                    system.viewBookings(scanner);
                    break;
                case 6:
                    system.attendLesson(scanner);
                    break;
                case 7:
                    system.viewAverageRatingAndNumCustomers();
                    break;
                case 8:
                    system.generateChampionReport();
                
                case 9:
                    System.out.println("Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
    
            System.out.println(); // Print blank line for spacing
        }
    }
}

class Booking {
private final String name;
private final String lesson;
private boolean attended;
private int rating;
public Booking(String name, String lesson) {
    this.name = name;
    this.lesson = lesson;
}

public String getName() {
    return name;
}

public String getLesson() {
    return lesson;
}

public boolean isAttended() {
    return attended;
}

public void setAttended(boolean attended) {
    this.attended = attended;
}

public int getRating() {
    return rating;
}

public void setRating(int rating) {
    this.rating = rating;
}
}