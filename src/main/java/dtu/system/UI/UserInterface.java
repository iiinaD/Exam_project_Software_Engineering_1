package dtu.system.UI;

import dtu.system.app.Application;
import dtu.system.app.OperationNotAllowedException;
import dtu.system.domain.Activity;
import dtu.system.domain.HalfHours;
import dtu.system.domain.Project;
import dtu.system.domain.Worker;

import java.util.Scanner;

public class UserInterface {
    public static void main(String[] args) throws OperationNotAllowedException {
        // Daniel
        Application app = new Application();
        Scanner terminal = new Scanner(System.in);
        boolean isExitChosen = false;
        while (!isExitChosen) {
            System.out.println();
            System.out.println("----- Login menu -----");
            int numMenuItems = printLoginMenu();
            System.out.print("Please choose an item from the menu list\n> ");
            int input = getIntInput(terminal,numMenuItems,"The number has to correspond to one of the menu items.");
            if (input == 1) {
                System.out.print("Please input your worker initials\n> ");
                String initials = terminal.next();
                try {
                    app.logIn(initials);
                    System.out.println("Worker " + initials + " is now logged in");
                    // Load main menu
                    mainMenu(app, terminal);
                } catch (OperationNotAllowedException e) {
                    System.out.println(e.getMessage() + "\n");
                }
            } else if (input == 2) {
                System.out.print("Please input the worker's initials\n> ");
                String initials = terminal.next();
                try {
                    Worker newWorker = new Worker(initials);
                    app.addNewWorker(newWorker);
                    System.out.println("The worker " + initials + " has been added\n");
                } catch (OperationNotAllowedException | IllegalArgumentException e) {
                    System.out.println(e.getMessage() + "\n");
                }

            } else if (input == 3) {
                isExitChosen = true;
            }
        }
        System.out.println("Program terminated.");
    }
    public static void mainMenu(Application app, Scanner terminal) {
        // Daniel
        while (app.getLoggedInStatus()) {
            System.out.println();
            System.out.println("----- Main menu -----");
            int numMenuItems = printMainMenu();
            System.out.print("Please choose an item from the menu list\n> ");
            int input = getIntInput(terminal,numMenuItems,"The number has to correspond to one of the menu items.");
            if (input == 1) {
                // empty the rest of the line before input
                terminal.nextLine();
                System.out.print("Please input the name of the project\n> ");
                String projectName = terminal.nextLine();
                System.out.print("\nPlease input the initials of the project leader (if not yet known enter 1)\n> ");
                String projectLeader = terminal.next();
                try {
                    Project createdProject;
                    if (projectLeader.equals("1")) {
                        createdProject = app.createProject(projectName);
                    } else {
                        createdProject = app.createProject(projectName, app.getWorkerWithInitials(projectLeader));
                    }
                    System.out.println(createdProject.getProjectName() + " was created and given the number " + createdProject.getProjectNumber());

                } catch (OperationNotAllowedException e) {
                    System.out.println(e.getMessage() + "\n");
                }
            } else if (input == 2) {
                System.out.print("Please input the id of the project\n> ");
                int projectNumber = terminal.nextInt();
                try {
                    Project accessProject = app.getProjectWithNumber(projectNumber);
                    projectMenu(app,terminal,accessProject);
                } catch (OperationNotAllowedException e) {
                    System.out.println(e.getMessage() + "\n");
                }
            } else if (input == 3) {
                System.out.println("3. Register working hours");
                System.out.println("Coming soon...");
            } else if (input == 4) {
                app.logOut();
                System.out.println("Worker has been logged out");
            }
        }
    }

    private static void projectMenu(Application app, Scanner terminal, Project project) throws OperationNotAllowedException {
        // Daniel
        boolean returnToMainMenu = false;
        while (!returnToMainMenu) {
            System.out.println();
            System.out.println("----- " + project.getProjectName() + " (" + project.getProjectNumber() + ")" + " -----");
            int numMenuItems = printProjectMenu();
            System.out.print("Please choose an item from the menu list\n> ");
            int input = getIntInput(terminal,numMenuItems,"The number has to correspond to one of the menu items.");
            if (input == 1) {
                try {
                    terminal.nextLine();
                    System.out.print("Please input the name of the activity\n> ");
                    String activityName = terminal.nextLine();
                    terminal.nextLine();
                    System.out.print("Please input the description of the activity (if not yet known enter 1)\n> ");
                    String activityDescription = terminal.nextLine();
                    if (activityDescription.equals("1")) {
                        activityDescription = "";
                    }
                    Activity activity = app.addActivityToProjectWithNameAndDescription(project,activityName,activityDescription);;
                    System.out.println("The activity " + activityName + " with the id " + activity.getActivityId() + " was created");
                } catch (OperationNotAllowedException e) {
                    System.out.println(e.getMessage() + "\n");
                }
            } else if (input == 2) {
                System.out.print("Please input the id of the activity\n> ");
                String activityId = terminal.next();
                try {
                    Activity accessActivity = app.getActivityFromProject(project.getProjectNumber(),activityId);
                    activityMenu(app,terminal,project,accessActivity);
                } catch (OperationNotAllowedException e) {
                    System.out.println(e.getMessage() + "\n");
                }
            } else if (input == 3) {
                // empty the rest of the line before input
                terminal.nextLine();
                System.out.print("What is the new name of this project?\n> ");
                String newProjectName = terminal.nextLine();
                app.changeProjectName(project.getProjectNumber(), newProjectName);
                System.out.println("Project name changed to " + newProjectName);
            } else if (input == 4) {
                System.out.println("What is the initials of the new project leader?");
                String newProjectLeader = terminal.next();
                try {
                    app.setProjectLeader(project.getProjectNumber(), newProjectLeader);
                    System.out.println("The new project leader is " + newProjectLeader);
                } catch (OperationNotAllowedException e) {
                    System.out.println(e.getMessage() + "\n");
                }
            } else if (input == 5) {
                try {
                    app.markProjectFinished(project);
                    System.out.println("Project is now officially done!");
                } catch (OperationNotAllowedException e) {
                    System.out.println(e.getMessage() + "\n");
                }
            } else if (input == 6) {
                System.out.println(app.getProjectOverview(project.getProjectNumber()));
            } else if (input == 7) {
                returnToMainMenu = true;
            }
        }
    }

    private static void activityMenu(Application app, Scanner terminal, Project project, Activity activity) throws OperationNotAllowedException {
        // Daniel
        boolean returnToProjectMenu = false;
        while (!returnToProjectMenu) {
            System.out.println();
            System.out.println("----- " + project.getProjectName() + " (" + project.getProjectNumber() + ")" + " -----");
            System.out.println("----- " + activity.getActivityName() + " (" + activity.getActivityId() + ")" + " -----");
            int numMenuItems = printActivityMenu();
            System.out.print("Please choose an item from the menu list\n> ");
            int input = getIntInput(terminal,numMenuItems,"The number has to correspond to one of the menu items.");
            if (input == 1) {
                System.out.println("Please input the initials of the worker\n> ");
                String workerInitials = terminal.next();
                app.addWorkerToActivity(project.getProjectNumber(), activity.getActivityId(), workerInitials);
                System.out.println("The worker " + workerInitials + " was added to this activity.");
            } else if (input == 2) {
                // empty the rest of the line before input
                terminal.nextLine();
                System.out.print("What is the new name of this activity?\n> ");
                String newActivityName = terminal.nextLine();
                // set activity name function
                System.out.println("Need to add a setActivityName function in the app");
                System.out.println("Activity name changed to " + newActivityName);
            } else if (input == 3) {
                // empty the rest of the line before input
                terminal.nextLine();
                System.out.print("What is the new description of this activity?\n> ");
                String newActivityDesc = terminal.nextLine();
                app.setActivityDescription(activity,newActivityDesc);
                System.out.println("Activity description was changed.");
            } else if (input == 4) {
                System.out.print("Please input the budgetted time for this activity in the format: hrs min  (fx 12 45)\n> ");
                int hours = terminal.nextInt();
                int min = terminal.nextInt();
                HalfHours budgetTime = new HalfHours(hours, min);
                app.setActivityBudgetTime(activity,budgetTime);
                System.out.println("The budgeted time was set to: " + budgetTime.getTime() + " hours.");
            } else if (input == 5) {
                System.out.println("Please input the start week and year for this activity in the format: week year (fx 34 2023)\n> ");
                int startWeek = terminal.nextInt();
                int startYear = terminal.nextInt();
                System.out.println("Please input the end week and year for this activity in the format: week year (fx 34 2023)\n> ");
                int endWeek = terminal.nextInt();
                int endYear = terminal.nextInt();
                app.activityPlanStartAndEnd(project.getProjectNumber(), activity.getActivityId(), startWeek, endWeek, startYear, endYear);
                System.out.println("The start and end week was changed.");
            } else if (input == 6) {
                System.out.println(app.getActivityOverview(activity.getActivityId()));
            } else if (input == 7) {
                returnToProjectMenu = true;
            }
        }
    }

    public static int getIntInput(Scanner terminal,int numberOfMenuItems,String message) {
        // Daniel
        // Check for a valid number
        int choice;
        while (true) {
            boolean intInput = terminal.hasNextInt();
            if (intInput) {
                choice = terminal.nextInt();
                if (choice <= numberOfMenuItems && choice > 0) {
                    break;
                }
            } else {
                terminal.next();
            }
            System.out.println("\n" + message);
            System.out.print("Please try again\n> ");
        }
        System.out.println();
        return choice;
    }
    public static int printLoginMenu() {
        // Daniel
        System.out.println("1. Log in");
        System.out.println("2. Add new worker");
        System.out.println("3. Exit program");
        return 3;
    }
    public static int printMainMenu() {
        // Daniel
        System.out.println("1. Create a project");
        System.out.println("2. Access a project");
        System.out.println("3. Register working hours");
        System.out.println("4. Logout and return to login screen");
        return 4;
    }

    public static int printProjectMenu() {
        // Daniel
        System.out.println("1. Add an activity");
        System.out.println("2. Access an acivity");
        System.out.println("3. Change project name");
        System.out.println("4. Set new project leader");
        System.out.println("5. Mark project as finished");
        System.out.println("6. Print project info");
        System.out.println("7. Return to main menu");
        return 7;
    }

    public static int printActivityMenu() {
        // Daniel
        System.out.println("1. Add worker to activity");
        System.out.println("2. Change name of activity");
        System.out.println("3. Change description of activity");
        System.out.println("4. Change the budgeted time on an activity");
        System.out.println("5. Set the start and end week of the activity");
        System.out.println("6. Print activity info");
        System.out.println("7. Return to project menu");
        return 7;
    }
}
