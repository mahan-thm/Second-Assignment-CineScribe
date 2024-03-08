import java.util.ArrayList;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        // TODO --> complete main function
        Scanner input = new Scanner(System.in);
        Movie myMovie = new Movie(new ArrayList<>(), "", 0);
        boolean secondWhileBreak = false;
        System.out.println("Give me a name of a movie!");
        while (true) {
            String movieName = input.nextLine();
            String movieJson = myMovie.getMovieData(movieName);
            if (movieJson == "{\"Response\":\"False\",\"Error\":\"Movie not found!\"}") {
                System.out.println("invalid name... Movie not found");
                continue;
            }
            while (true) {
                System.out.println("1. selected movie\n2. year\n3. imdb votes\n4. imdb rating\n5. actors\n6. select another movie\n7. EXIT");
                int movieMenuOrder = Integer.valueOf(input.nextLine());
                if (movieMenuOrder == 6) {
                    break;
                } else if (movieMenuOrder == 7) {
                    secondWhileBreak = true;
                    break;
                }
                runMovieMenu(movieName, movieMenuOrder);
            }
            if (secondWhileBreak) {
                break;
            }
        }

    }


    public static void runMovieMenu(String name, int order) {
        Movie myMovie = new Movie(new ArrayList<>(), "", 0);
        String movieJson = myMovie.getMovieData(name);

        switch (order) {
            case 1:
                System.out.println(myMovie.getTitleViaApi(movieJson));
                break;
            case 2:
                System.out.println(myMovie.getYearViaApi(movieJson));
                break;
            case 3:
                System.out.println(myMovie.getImdbVotesViaApi(movieJson));
                break;
            case 4:
                System.out.println(myMovie.getRatingViaApi(movieJson));
                break;
            case 5:
                System.out.println(myMovie.getActorListViaApi(movieJson));
                System.out.println("Type the actor name to see actor info");
                Scanner input = new Scanner(System.in);
                while (true) {
                    String actorName = input.nextLine();
                    Actors myActor = new Actors(name, true);
                    String actorJson = myActor.getActorData(name);
                    if (actorJson == null) {
                        System.out.println("ERROR: Invalid name... Actor not found");
                        continue;
                    }
                    System.out.println("1. selected actor\n2. net worth\n3. gender\n4. height\n5. birth\n6. age or date of death\n7. BACK");
                    while (true) {
                        int actorMenuOrder = Integer.valueOf(input.nextLine());
                        runActorMenu(actorName, actorMenuOrder);

                    }

                }
        }
        return;
    }


    public static void runActorMenu(String name, int order) {
        // TODO
        Actors myActor = new Actors(name, true);
        String json = myActor.getActorData(name);
        Actors estefade = new Actors(name, true);

        switch (order) {
            case 1:
                System.out.println(myActor.getNameViaApi(json));
                break;
            case 2:
                System.out.println(myActor.getNetWorthViaApi(json));
                break;
            case 3:
                System.out.println(myActor.getGenderViaApi(json));
                break;
            case 4:
                System.out.println(myActor.getHeightViaApi(json));
                break;
            case 5:
                System.out.println(myActor.getDateOfBirthViaApi(json));
                break;
            case 6:
                if (myActor.isAlive(json))
                    System.out.println(myActor.getAgeViaApi(json));
                else if (!myActor.isAlive(json))
                    System.out.println(myActor.getDateOfDeathViaApi(json));
                break;
            default:
                System.out.println("invalid choice");
                break;
        }

//        String inputName = "ana de armas";
//        String result = myActor.getActorData(inputName);
//        System.out.println(result);
//        System.out.println(myActor.getNetWorthViaApi(result));
    }
}