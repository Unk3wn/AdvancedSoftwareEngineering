import {ICountry} from "../country/ICountry";

export interface IManufacturer {
  uuid :string,
  name : string,
  originCountry : ICountry
}
