import moment from 'moment';
const xLabel = ["Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"]
const DAYS_PER_WEEK = 7;

export default function GetHeatMapWeek(data_keys, data_values, dateIdx, LABELS_IDX) { 

        let newRow = []
        let newName = '';

        for (let j=0; j<DAYS_PER_WEEK; j++) {   // create dates on y axis
            
            if (j === 0) {
                const dateEnd = moment(new Date(data_keys[dateIdx])).add(7, 'days').toDate();
                newName = data_keys[dateIdx] + ' to ' + dateEnd.getFullYear() + '-' + formatMonth(dateEnd) + '-' + formatDate(dateEnd);
            }
            const labels = data_values[dateIdx]
            newRow.push({
                x: xLabel[j],
                y: findMax(labels, LABELS_IDX)
            });
            dateIdx++; // increment one to iterate through all data values
        }
        const updatedDateIdx = dateIdx;

        const weekData =  {
            name: newName,
            data: newRow
        }

        return {weekData, updatedDateIdx}

}

const findMax = (labels, LABELS_IDX) => { // if a label exists, find the most frequent label
    let maxLabel = ['-1',-1]
    if (labels) {
        for (const key in labels) {
            if (parseInt(labels[key]) > maxLabel[1]) {
                maxLabel = [key, labels[key]]
            }
        }
        return LABELS_IDX[maxLabel[0]];
        
    } else {
        return LABELS_IDX['-1']; // return '-1' as a label if there is no data  
    }
}

const formatDate = (date) => { return ("0" + date.getDate()).slice(-2) } // format date to have 0 in front if single digit

const formatMonth = (date) => { return ("0" + (date.getMonth() + 1)).slice(-2) } // format month to have 0 in front if single digit
