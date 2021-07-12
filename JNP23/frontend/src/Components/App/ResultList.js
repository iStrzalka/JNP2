import Advert from './Advert'
import { Container } from '@material-ui/core';


const ResultList = ({ adverts }) => {

    return (
        <Container className='result_list'>
            {adverts.map((advert) => (<Advert key={advert.id} advert={advert} />))}
        </Container>
    )
}

export default ResultList