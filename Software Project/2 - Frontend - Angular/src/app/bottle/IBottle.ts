import {IManufacturer} from "../manufacturer/IManufacturer";

export interface IBottle {
  uuid : string,
  label : string,
  price : number,
  yearOfManufacture : number
  manufacturer : IManufacturer,
  forSale : boolean,
  favorite : boolean;
  unsaleable : boolean
}
