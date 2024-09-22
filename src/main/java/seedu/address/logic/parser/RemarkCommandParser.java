package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.RemarkCommand;
import seedu.address.logic.parser.exceptions.ParseException;


/**
 * Parses input arguments and creates a new RemarkCommand object.
 * <p>
 * This parser handles the parsing of the remark command input, extracting necessary
 * information such as the target index of the remark and the actual remark content itself.
 * It uses a specified prefix to distinguish the remark content within the command arguments.
 * </p>
 */
public class RemarkCommandParser implements Parser<RemarkCommand> {

    private static final Prefix PREFIX_REMARK = new Prefix("r/");

    /**
     * Parses the given {@code String} of arguments in the context of the RemarkCommand
     * and returns a RemarkCommand object for execution.
     * <p>
     * The method handles parsing of the index and the remark content from the user input.
     * It uses {@link ArgumentTokenizer} to separate command parts based on the prefix used,
     * and then attempts to parse the index and the remark content.
     * If the parsing fails due to incorrect format, it throws a ParseException.
     * </p>
     *
     * @param args full command args string
     * @return the prepared command
     * @throws ParseException if the user input does not conform to the expected format
     * @see seedu.address.logic.parser.ArgumentTokenizer
     */
    public RemarkCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_REMARK);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    RemarkCommand.MESSAGE_USAGE), ive);
        }

        String remark = argMultimap.getValue(PREFIX_REMARK).orElse("");

        return new RemarkCommand(index, remark);
    }
}
