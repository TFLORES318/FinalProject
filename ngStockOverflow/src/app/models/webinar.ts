import { User } from './user';
import { WebinarRating } from './webinar-rating';

export class Webinar {
  id: number;
  title: string;
  description: string;
  dateTime: Date;
  createdAt: Date;
  updateDate: Date;
  picture: string;
  meetingLink: string;
  maxAttendees: number;
  enabled: boolean;
  userCreator: User;
  usersAttending: User[];
  webinarRatings: WebinarRating[];

  constructor(
    id?: number,
    title?: string,
    description?: string,
    dateTime?: Date,
    createdAt?: Date,
    updateDate?: Date,
    picture?: string,
    meetingLink?: string,
    maxAttendees?: number,
    enabled?: boolean,
    userCreator?: User,
    usersAttending?: User[],
    webinarRatings?: WebinarRating[]
  ){
    this.id = id;
    this.title = title;
    this.description = description;
    this.dateTime = dateTime;
    this.createdAt = createdAt;
    this.updateDate = updateDate;
    this.picture = picture;
    this.meetingLink = meetingLink;
    this.maxAttendees = maxAttendees;
    this.enabled= enabled;
    this.userCreator = userCreator;
    this.usersAttending = usersAttending;
    this.webinarRatings = webinarRatings;
  }
}
