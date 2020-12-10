import { Admin } from '../admin/admin'
import { Contact } from '../contact/contact'
import { Site } from '../site/site'


export class Hospital {
    hospitalName:string
    hospitalType:string
    institutionType:string
    hospitalAccredition:string
    hospitalRegistrationNo:string
    verifiedInd:number
    admin:Admin
    site:Site
    contact:Contact
}
