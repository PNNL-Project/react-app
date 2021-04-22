import moment from 'moment';

const END_DATE = moment().subtract(1, 'days').toDate();

export default function GetStartDate() {
    let offset = (7 - moment(END_DATE).subtract(28, 'days').toDate().getDay()) % 7;
    if (offset === 0) {
        offset = 7;
    }
    let startDate = moment(END_DATE).subtract(28, 'days').add(offset, 'days').toDate();
    return startDate;
}