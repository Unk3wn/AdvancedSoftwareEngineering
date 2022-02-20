import {IManufacturer} from "../manufacturer/IManufacturer";

export interface IBottle {
  id : string,
  label : string,
  price : number,
  yearOfManufacture : number
  manufacturer : IManufacturer,
  forSale : boolean,
  favorite : boolean;
  unsaleable : boolean
}
