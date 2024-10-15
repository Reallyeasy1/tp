package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ATTENDANCEDATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENTID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIALCLASS;

import java.util.Date;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.StudentId;
import seedu.address.model.student.TutorialClass;

/**
 * Records a student's attendance for a particular date and tutorial class.
 * This command takes a student ID, tutorial class ID, and an optional attendance date.
 * If the tutorial class exists and the student is part of the class, the attendance is recorded successfully.
 * Otherwise, it returns an error message indicating the class or student is invalid.
 */
public class AttendCommand extends Command {
    public static final String COMMAND_WORD = "attend";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Record Student attendance for particular date and "
            + "tutorial class. "
            + "Parameters: "
            + PREFIX_STUDENTID + "STUDENT_ID "
            + PREFIX_TUTORIALCLASS + "TUTORIAL_CLASS "
            + "[" + PREFIX_ATTENDANCEDATE + "ATTENDANCE DATE] \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_STUDENTID + "1001 "
            + PREFIX_TUTORIALCLASS + "1001 "
            + PREFIX_ATTENDANCEDATE + "2024/02/21";
    public static final String MESSAGE_SUCCESS = "Attendance recorded";
    public static final String MESSAGE_FAILURE = "Tutorial Class doesn't exist or Student not in Tutorial Class!";

    private final StudentId studentId;
    private final TutorialClass tutorialClass;
    private final Date tutDate;

    /**
     * Creates an AttendCommand to record a student's attendance.
     *
     * @param studentId The ID of the student whose attendance is being recorded.
     * @param tutorialClass The tutorial class in which the student is attending.
     * @param tutDate The date of the attendance record. Can be null if not specified.
     */
    public AttendCommand(StudentId studentId, TutorialClass tutorialClass, Date tutDate) {
        requireNonNull(studentId);
        requireNonNull(tutorialClass);
        requireNonNull(tutDate);
        this.studentId = studentId;
        this.tutorialClass = tutorialClass;
        this.tutDate = tutDate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.setStudentAttendance(studentId, tutorialClass, tutDate)) {
            throw new CommandException(MESSAGE_FAILURE);
        }

        return new CommandResult(MESSAGE_SUCCESS + "\n" + this);
    }

    @Override
    public String toString() {
        return "Student: " + studentId.toString() + "\n" + "Date: " + tutDate.toString() + "\n"
                + "Tutorial ID: " + tutorialClass.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AttendCommand otherAttendCommand)) {
            return false;
        }

        return studentId.equals(otherAttendCommand.studentId)
                && tutorialClass.equals(otherAttendCommand.tutorialClass)
                && tutDate.equals(otherAttendCommand.tutDate);
    }
}
