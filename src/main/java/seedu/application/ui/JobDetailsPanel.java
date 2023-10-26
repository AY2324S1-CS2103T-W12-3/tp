package seedu.application.ui;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import seedu.application.commons.core.LogsCenter;
import seedu.application.model.job.Job;

/**
 * Panel containing the details of the selected job.
 */
public class JobDetailsPanel extends UiPart<Region> {
    private static final String FXML = "JobDetailsPanel.fxml";

    private final Logger logger = LogsCenter.getLogger(JobDetailsPanel.class);

    private final Job job;

    // TODO Declare Interview List here

    @FXML
    private StackPane detailsPanelPlaceholder;
    @FXML
    private VBox jobDetailsPanel;
    @FXML
    private Label role;
    @FXML
    private Label company;
    @FXML
    private Label status;
    @FXML
    private Label deadline;
    @FXML
    private Label jobType;
    @FXML
    private Label industry;
    @FXML
    private Label address;
    @FXML
    private Label interviewTitle;

    /**
     * Creates a {@code JobDetailsPanel} with the selected {@code Job}.
     */
    public JobDetailsPanel(Job job) {
        super(FXML);
        this.job = job;
        role.setText(job.getRole().description);
        company.setText(job.getCompany().name);
        status.setText(job.getStatus().status);
        deadline.setText(job.getDeadline().deadline);
        jobType.setText(job.getJobType().jobType);
        industry.setText(job.getIndustry().industry);
        address.setText(job.getAddress().address);
        interviewTitle.setText("Interviews: ");
        //TODO Initialise Interview List here
    }
}

