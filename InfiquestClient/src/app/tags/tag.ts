export class Tag {
 
 tag_name:string;
 tag_id: number;
 tag_timestamp : number;
 tag_image: string;

 setTagName(tagname: string) : void
 {
   this.tag_name = tagname;
 }

 setTagTimeStamp(timestamp: number) : void
 {
   this.tag_timestamp = timestamp;
 }

 setTagImage(tagimageurl: string) : void
 {
   this.tag_image = tagimageurl;
 }

}


 