import { Post } from './post';

export class Comment {

  id: number;
  content: string;
  enabled: boolean;
  createDate: Date;
  // post: Post;

  constructor(id?:number, content?:string, enabled?:boolean, createDate?:Date) {
    this.id = id;
    this.content = content;
    this.enabled = enabled;
    this.createDate = createDate;
  }




}
