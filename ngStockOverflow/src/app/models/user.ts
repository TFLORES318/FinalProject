export class User {
  id: number;
  username: string;
  password: string;
  flair: string;
  firstName: string;
  lastName: string;
  email: string;
  role: string;
  profilePicture: string;
  createDate: Date;
  // enabled: boolean;

  constructor(id?: number, username?: string, password?: string, flair?: string,
     // tslint:disable-next-line: align
     firstName?: string, lastName?: string, email?: string, role?: string, profilePicture?: string, createDate?: Date){
       this.id = id;
       this.username = username;
       this.password = password;
       this.flair = flair;
       this.firstName = firstName;
       this.lastName = lastName;
       this.email = email;
       this.role = role;
       this.profilePicture = profilePicture;
       this.createDate = createDate;
     }
}
