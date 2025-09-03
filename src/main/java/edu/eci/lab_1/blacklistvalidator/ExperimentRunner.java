package edu.eci.lab_1.blacklistvalidator;

public class ExperimentRunner {
    public static void main(String[] args) {
        String ip = "202.24.34.55";
        int cores = Runtime.getRuntime().availableProcessors();
        int[] threadCounts = {1, cores, cores * 2, 50, 100};

        System.out.println("Validación de IP: " + ip);
        System.out.println("Núcleos detectados: " + cores);
        System.out.println("========================================");
        System.out.printf("%-15s %-20s\n", "Hilos", "Tiempo (ms)");
        System.out.println("----------------------------------------");

        for (int threads : threadCounts) {
            HostBlackListsValidator validator = new HostBlackListsValidator();
            long start = System.currentTimeMillis();
            validator.checkHost(ip, threads);
            long end = System.currentTimeMillis();
            System.out.printf("%-15d %-20d\n", threads, (end - start));
        }
    }
}

/*Validación de IP: 202.24.34.55
Núcleos detectados: 6
========================================
Hilos           Tiempo (ms)
----------------------------------------
1               123991
6               24100
12              12636
50              3164
100             1581 

ago. 22, 2025 9:11:48 P.áM. edu.eci.arsw.spamkeywordsdatasource.HostBlacklistsDataSourceFacade reportAsNotTrustworthy
INFORMACIËN: HOST 202.24.34.55 Reported as NOT trustworthy
ago. 22, 2025 9:11:48 P.áM. edu.eci.arsw.blacklistvalidator.HostBlackListsValidator checkHost
INFORMACIËN: Checked Black Lists: 5 of 80,000
1               123991
ago. 22, 2025 9:12:12 P.áM. edu.eci.arsw.spamkeywordsdatasource.HostBlacklistsDataSourceFacade reportAsNotTrustworthy
INFORMACIËN: HOST 202.24.34.55 Reported as NOT trustworthy
ago. 22, 2025 9:12:12 P.áM. edu.eci.arsw.blacklistvalidator.HostBlackListsValidator checkHost
INFORMACIËN: Checked Black Lists: 5 of 80,000
6               24100
ago. 22, 2025 9:12:25 P.áM. edu.eci.arsw.spamkeywordsdatasource.HostBlacklistsDataSourceFacade reportAsNotTrustworthy
INFORMACIËN: HOST 202.24.34.55 Reported as NOT trustworthy
ago. 22, 2025 9:12:25 P.áM. edu.eci.arsw.blacklistvalidator.HostBlackListsValidator checkHost
INFORMACIËN: Checked Black Lists: 5 of 80,000
12              12636
ago. 22, 2025 9:12:28 P.áM. edu.eci.arsw.spamkeywordsdatasource.HostBlacklistsDataSourceFacade reportAsNotTrustworthy
INFORMACIËN: HOST 202.24.34.55 Reported as NOT trustworthy
ago. 22, 2025 9:12:28 P.áM. edu.eci.arsw.blacklistvalidator.HostBlackListsValidator checkHost
INFORMACIËN: Checked Black Lists: 5 of 80,000
50              3164
ago. 22, 2025 9:12:29 P.áM. edu.eci.arsw.spamkeywordsdatasource.HostBlacklistsDataSourceFacade reportAsNotTrustworthy
INFORMACIËN: HOST 202.24.34.55 Reported as NOT trustworthy
ago. 22, 2025 9:12:29 P.áM. edu.eci.arsw.blacklistvalidator.HostBlackListsValidator checkHost
INFORMACIËN: Checked Black Lists: 5 of 80,000
100             1581 */