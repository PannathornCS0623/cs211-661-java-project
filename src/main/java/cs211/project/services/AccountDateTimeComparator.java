package cs211.project.services;

import cs211.project.models.Account;
import java.util.Comparator;

public class AccountDateTimeComparator implements Comparator<Account> {
    @Override
    public int compare(Account o1, Account o2) {
        if (!o2.getDate().equals(o1.getDate())) {
            if (o2.getDate().isAfter(o1.getDate())) {
                return 1;
            } else if (o2.getDate().isBefore(o1.getDate())) {
                return -1;
            } else {
                return 0;
            }
        } else {
            if (o2.getTime().isAfter(o1.getTime())) {
                return 1;
            } else if (o2.getTime().isBefore(o1.getTime())) {
                return -1;
            } else {
                return 0;
            }
        }
    }
}
