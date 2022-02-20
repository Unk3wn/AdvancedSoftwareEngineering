import {IManufacturer} from "../manufacturer/IManufacturer";
import {ISeries} from "../series/ISeries";

export interface IBottle {
  uuid : string,
  label : string,
  price : number,
  yearOfManufacture : number
  manufacturer : IManufacturer,
  forSale : boolean,
  favorite : boolean;
  unsaleable : boolean,
  series : ISeries
}
