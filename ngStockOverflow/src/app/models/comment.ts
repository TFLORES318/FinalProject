export class Comment {

  id: number;
  content: string;
  enabled: boolean;
  createDate: Date;

  constructor(id?:number, content?:string, enabled?:boolean, createDate?:Date) {
    this.id = id;
    this.content = content;
    this.enabled = enabled;
    this.createDate = createDate;
  }




}
