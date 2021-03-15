package dev.ghimire.entities;




import javax.persistence.*;


@Entity
@Table(name = "expense")
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="expense_id")
    private int expenseId;

    @Column(name = "amount")
    private double amount;

    @Column(name = "reason")
    private String reason;

    @Column(name = "submitted_date")
    private long submittedDate;

    @Column(name = "decision_date")
    private long decisionDate;

    @Column(name = "status")
    private String status;

    @Column(name = "employee_id")
    @JoinColumn(name = "employee_id")
    private int employeeId;

    @Column(name = "manager_id")
    @JoinColumn(name = "manager_id")
    private int managerId;





//    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
//    sdf.setTimeZone(TimeZone.getTimeZone(timeZone));
//    Long dateLong=Long.parseLong(sdf.format(epoch*1000));

    public Expense(){

    }
    public Expense(int expenseId,String status,int amount)
    {
        this.expenseId=expenseId;
        this.status=status;
        this.amount=amount;
    }

    public Expense(int expenseId, double amount, String reason, int employeeId) {
        this.expenseId = expenseId;
        this.amount = amount;
        this.reason = reason;
        this.submittedDate = System.currentTimeMillis();
        this.employeeId = employeeId;
        this.decisionDate=0;
        this.status="pending";
        this.managerId=1;
    }

    public int getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(int expenseId) {
        this.expenseId = expenseId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public long getSubmittedDate() {
        return submittedDate;
    }

    public void setSubmittedDate(long submittedDate) {
        this.submittedDate = submittedDate;
    }

    public long getDecisionDate() {
        return decisionDate;
    }

    public void setDecisionDate(long decisionDate) {
        this.decisionDate = decisionDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "expenseId=" + expenseId +
                ", amount=" + amount +
                ", reason='" + reason + '\'' +
                ", submittedDate=" + submittedDate +
                ", decisionDate=" + decisionDate +
                ", status='" + status + '\'' +
                ", employeeId=" + employeeId +
                ", managerId=" + managerId +
                '}';
    }




}
