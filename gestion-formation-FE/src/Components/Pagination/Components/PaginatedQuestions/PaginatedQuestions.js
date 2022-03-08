import React, {useEffect, useState} from "react";
import ReactPaginate from "react-paginate";
import ItemsQuestions from "./Components/ItemsQuestions/ItemsQuestions";

export default function PaginatedQuestions(props){

    const [currentItems, setCurrentItems] = useState(null)
    const [pageCount, setPageCount] = useState(0)
    const [itemOffset, setItemOffset] = useState(0)
    const [isLoad, setIsLoad] = useState(false)

    const modidyItemOffset = (i) => {
        setItemOffset(i)
    }
    const addCurrentItems = (i) => {
        setCurrentItems(i)
    }
    const modifyPageCount = (p) => {
        setPageCount(p)
    }


    const handlePageClick = (e) => {
        const newOffset = (e.selected * props.itemsPerPage) % props.value.length;
        modidyItemOffset(newOffset)
    }

    useEffect(() => {
        const endOffset = itemOffset + props.itemsPerPage
        addCurrentItems(props.value.slice(itemOffset, endOffset));
        modifyPageCount(Math.ceil(props.value.length / props.itemsPerPage))
        setIsLoad(true)
    }, [itemOffset, props.itemsPerPage])

    return (
        <>

            {isLoad && (

                <>
                    <ItemsQuestions currentItems={currentItems}/>

                    {pageCount > 1 &&
                        <ReactPaginate
                            pageCount={pageCount}
                            nextLabel={"Page suivante"}
                            onPageChange={handlePageClick}
                            pageRangeDisplayed={5}
                            marginPagesDisplayed={2}
                            previousLabel={"Page précédente"}
                            renderOnZeroPageCount={null}
                            breakLabel={"..."}
                            pageClassName="page-item"
                            pageLinkClassName="page-link"
                            previousClassName="page-item"
                            previousLinkClassName="page-link"
                            nextClassName="page-item"
                            nextLinkClassName="page-link"
                            breakClassName="page-item"
                            breakLinkClassName="page-link"
                            containerClassName="pagination justify-content-center"
                            activeClassName="active"
                        />
                    }

                </>

            )}

        </>
    );

}