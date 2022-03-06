import React, {useEffect, useState} from "react";
import axios from "axios";
import Items from "../Items/Items";
import ReactPaginate from "react-paginate";

export default function PaginatedItems(props){

    const [currentItems, setCurrentItems] = useState(null)
    const [pageCount, setPageCount] = useState(0)
    const [itemOffset, setItemOffset] = useState(0)
    
    useEffect( () => {

        const endOffset = itemOffset + props.itemsPerPage
        setCurrentItems(props.value.slice(itemOffset, endOffset));
        setPageCount(Math.ceil(props.value.length / props.itemsPerPage))

    }, [itemOffset, props.itemsPerPage])

    const handlePageClick = (e) => {
        const newOffset = (e.selected * props.itemsPerPage) % props.value.length;
        setItemOffset(newOffset)
    }

    return (
        <>
            <Items currentItems={currentItems}/>
            <ReactPaginate
                pageCount={pageCount}
                nextLabel={"next >"}
                onPageChange={handlePageClick}
                pageRangeDisplayed={5}
                previousLabel={"< previous"}
                renderOnZeroPageCount={null}
                breakLabel={"..."}
            />
        </>
    );

}