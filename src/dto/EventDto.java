package dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Data-transfer object to create event history table
 * 12.03.2020
 */

@Getter
@Setter
@AllArgsConstructor
public class EventDto implements Serializable {
    private String dateOfEvent;
    private String timeOfEvent;
    private int categoryLabelId;
    private int operationSum;
}
