import { CommentRating } from './comment-rating';

export class Comment {

  id: number;
  content: string;
  enabled: boolean;
  createDate: Date;
  commentRatings: CommentRating[];

  constructor(id?:number, content?:string, enabled?:boolean, createDate?:Date, commentRatings?:CommentRating[]) {
    this.id = id;
    this.content = content;
    this.enabled = enabled;
    this.createDate = createDate;
    this.commentRatings = commentRatings;
  }




}
