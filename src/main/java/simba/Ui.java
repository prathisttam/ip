package simba;

public class Ui {
    public void showWelcomeMessage() {
        String logo =
                "⠀                        ⢠⡴⣴⣠⣀⣀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡠⠃⣀⣀⣉⣐⣒⠊⠡⠤⢄⣀⠀⠀⠀⠀⠀⠀⢀⡀⠤⠄⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡀⠀⠀⠀⠀⠀⠀⠀⢀⡠⣊⣴⣿⣿⣿⣿⣷⡶⠄⠲⣿⣶⣦⣬⣑⠢⠀⠤⢂⣥⣶⣿⡶⠄⠑⡄⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⠀⠀⠀⠀⡠⢊⣤⣦⣬⣑⡢⢄⣀⣀⠔⣡⣾⣿⣿⣿⣿⣿⣿⣿⣿⣶⣶⣿⣿⣿⡿⢿⣷⣶⣿⣿⣿⣿⢃⣀⠀⠀⠘⡄⠀⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⠀⠀⠀⡐⠐⠉⠉⠙⢿⣿⣿⣷⣶⣴⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⢋⣤⣤⣈⢿⣿⣿⣿⠇⡾⠿⣧⠀⠀⢡⠀⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⠀⠀⢀⠁⠀⢠⣶⣿⡎⢿⣿⣿⣿⣿⣿⣿⣿⣿⡿⣻⣿⣿⣿⣿⣿⣿⣿⡿⢡⣿⡿⢿⣿⣧⢿⣿⡿⣠⣾⣿⣿⠀⠀⢸⠀⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⠀⠀⢸⠀⠀⢸⣿⣭⣛⡘⣿⣿⣿⠿⢛⡋⠩⠥⣦⠹⣿⣿⣿⣿⣿⣿⣿⠇⡾⠋⠀⡡⡸⣿⡌⡿⢑⣭⣽⣿⡿⠀⠀⡘⠀⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⠀⠀⢸⠀⠀⢸⣿⣻⣭⡥⠘⣿⢇⡾⢡⠀⠀⠆⠘⣧⢻⣿⣿⢿⣿⣿⣿⠀⢃⠀⠀⠁⡅⠸⠇⡇⣶⣶⣶⡿⠁⠀⢠⠃⠀⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⠀⠀⠀⢣⠀⠀⠻⣿⣯⣴⣿⢸⡈⢅⡈⠦⣀⡠⠆⠹⠈⢁⣤⣽⣿⣿⣿⡀⣃⡑⠒⠊⠥⢚⣰⣿⠘⠛⠉⠀⠀⡠⠃⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠑⢄⠀⠈⠙⠛⠛⢸⣿⣷⣮⣭⣀⣬⡴⠢⣰⣿⣿⣿⣿⣿⣿⣷⠦⢘⠻⡿⠿⣿⣿⣿⡇⢀⠖⠒⠈⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠑⠢⠤⠀⢀⠈⣿⣿⣿⣿⡿⢋⣴⢑⣛⣛⠿⠿⠿⠟⣫⠖⠲⣸⣷⡆⣶⣾⣿⣿⢁⣾⡼⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⢀⣀⣠⣄⣀⣀⠀⠀⠀⢀⢜⣴⣉⢿⣿⠿⠦⢛⣿⣷⣄⣈⠙⠿⣶⠿⢟⣠⣾⣿⣿⡂⠶⣶⠌⣡⣭⣛⡃⣄⣠⣤⣤⣤⣤⣀⡀⠀⠀⠀⠀\n" +
                        "⠀⠀⡀⣴⣾⣿⣿⣿⠿⠿⠿⢿⣷⣖⣵⣿⢟⣫⣥⠄⠻⠟⡀⡹⢿⣿⣿⣿⣷⡦⠸⠿⣿⣿⣿⢟⢅⢛⢠⣬⣛⢿⣿⣿⠿⠿⠟⠿⠿⠿⠿⠛⠢⣦⣀⠀\n" +
                        "⣴⠟⣫⣤⡬⣡⣤⡖⣼⣿⣷⣶⣌⠻⠿⠚⣋⣥⡶⢊⣵⣦⡝⠐⣮⣝⣛⣋⣥⣶⣶⣶⣶⣤⣴⡿⣰⣝⢷⣬⡻⣷⠋⣤⣾⣿⣿⣎⢲⣶⣄⠻⣷⣦⠹⣧\n" +
                        "⣿⢸⣿⡏⢼⣿⣿⡆⢿⣿⣿⣿⣿⡧⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣾⣿⣿⣿⣿⠏⣼⣿⣿⡇⢸⣿⢀⡿\n" +
                        "⢻⡄⢿⣧⡘⢿⣿⣿⣦⡙⢿⣿⡿⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⢿⣿⡿⢋⣼⣿⣿⡟⣠⣿⠏⣼⠃\n" +
                        "⠀⠻⣌⠻⣷⣌⠻⢿⣿⣿⠆⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠐⢿⣿⠿⢋⣴⡿⠋⠜⠁⠀";

        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Simba");
        System.out.println("What can I do for you?\n" + logo);
        System.out.println("____________________________________________________________");
    }

    public void showExitMessage() {
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    public void showMenu() {
        System.out.println("\nChoose an option:");
        System.out.println("1 - Play the game Echo!");
        System.out.println("2 - Manage Tasks");
        System.out.println("3 - Exit");
        System.out.print("Your choice: ");
    }

    public void printDashedLine() {
        System.out.println("____________________________________________________________");
    }
}
