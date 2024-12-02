namespace ArrowheadServiceInstaller.Dtos;

using System.Collections.Generic;

public class ResponseRooot<T> where T : class
{
    public List<T> Data { get; set; }
    public int Count { get; set; }
}